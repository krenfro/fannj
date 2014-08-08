/* FannJ
 * Copyright (C) 2009 Kyle Renfro
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA. The text of license can be also found
 * at http://www.gnu.org/copyleft/lgpl.html
 */
package com.googlecode.fannj;

import com.sun.jna.Library;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;
import java.util.Map;

/**
 * <p>
 * A standard fully connected back-propagation neural network.
 * </p>
 * <p>
 * Not thread safe.
 * </p>
 * A Java binding to the Fast Artificial Neural Network (FANN) native library.
 * <p>
 * This class invokes native code. You must call close() to prevent memory
 * leakage.
 * </p>
 * 
 * @author krenfro
 * @see <a href="http://leenissen.dk/fann">Fast Artificial Neural Network</a>
 * @see <a href="https://jna.dev.java.net/#direct">JNA Direct Maping</a>
 */
public class Fann {

    static {
        NativeLibrary fann;
        if (Platform.isWindows()){
            fann = NativeLibrary.getInstance("fannfloat");
            Map options = fann.getOptions();
            options.put(Library.OPTION_CALLING_CONVENTION,  StdCallLibrary.STDCALL_CONVENTION);
            options.put(Library.OPTION_FUNCTION_MAPPER, new WindowsFunctionMapper());            
        }
        else{
            fann = NativeLibrary.getInstance("fann");
        }
        Native.register(fann);
    }
    
    protected Pointer ann;

    protected Fann() {
    }

    /**
     * Load an existing FANN definition from a file
     * 
     * @param file
     */
    public Fann(String file) {
        ann = fann_create_from_file(file);
    }

    /**
     * Create a new ANN with the provided layers.
     * 
     * @param layers
     */
    public Fann(List<Layer> layers) {
        if (layers == null)
            throw new IllegalArgumentException("layers == null");
        if (layers.isEmpty())
            throw new IllegalArgumentException("layers is empty");

        int[] neurons = new int[layers.size()];
        for (int x = 0; x < neurons.length; x++)
            neurons[x] = layers.get(x).size();

        ann = fann_create_standard_array(neurons.length, neurons);
        addLayers(layers);
    }

    protected void addLayers(List<Layer> layers) {

        for (int x = 1; x < layers.size(); x++) {
            Layer layer = layers.get(x);
            for (int n = 0; n < layer.size(); n++) {
                fann_set_activation_function(ann, layer.get(n)
                        .getActivationFunction().ordinal(), x, n);
                fann_set_activation_steepness(ann, layer.get(n).getSteepness(),
                        x, n);
            }
        }
    }

    public int getNumInputNeurons() {
        return fann_get_num_input(ann);
    }

    public int getNumOutputNeurons() {
        return fann_get_num_output(ann);
    }

    public int getTotalNumNeurons() {
        return fann_get_total_neurons(ann);
    }

    /**
     * Save this FANN to a file.
     * 
     * @param file
     * @return true on success
     */
    public boolean save(String file) {

        return fann_save(ann, file) == 0;
    }

    /**
     * Run the ANN on a set of inputs.
     * 
     * @param input
     *            length == numInputNeurons
     * @return the output of the ANN. (length = numOutputNeurons)
     */
    public float[] run(float[] input) {
        Pointer result = fann_run(ann, input);
        float[] output = result.getFloatArray(0, getNumOutputNeurons());
        return output;
    }

    /**
     * <p>
     * Frees allocated memory.
     * </p>
     * You must call this method when you are finished to prevent memory leaks.
     */
    public void close() {
        if (ann != null)
            fann_destroy(ann);
    }

    /**
     * Call {@link #close()} on garbage collection to catch memory leaks.
     */
    @Override
    public void finalize() throws Throwable {
        close();
        super.finalize();
    }

    /*
     * A JNA Direct Mapping implementation of the FANN library. This instance
     * should be more performant than #com.googlecode.fannj.jna.FannLibrary
     */
    protected static native Pointer fann_create_standard_array(int numLayers,
            int[] layers);

    protected static native Pointer fann_create_sparse_array(
            float connection_rate, int numLayers, int[] layers);

    protected static native Pointer fann_create_shortcut_array(int numLayers,
            int[] layers);

    protected static native float fann_get_MSE(Pointer ann);

    protected static native Pointer fann_run(Pointer ann, float[] input);

    protected static native void fann_destroy(Pointer ann);

    protected static native int fann_get_num_input(Pointer ann);

    protected static native int fann_get_num_output(Pointer ann);

    protected static native int fann_get_total_neurons(Pointer ann);

    protected static native void fann_set_activation_function(Pointer ann,
            int activation_function, int layer, int neuron);

    protected static native void fann_set_activation_steepness(Pointer ann,
            float steepness, int layer, int neuron);

    protected static native Pointer fann_get_neuron(Pointer ann, int layer,
            int neuron);

    protected static native Pointer fann_create_from_file(
            String configuration_file);

    protected static native int fann_save(Pointer ann, String file);
}
