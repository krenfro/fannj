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

import java.util.List;

/**
 * <p>
 * A standard backpropagation neural network, which is not fully connected and
 * which also has shortcut connections.
 * </p>
 * 
 * @author krenfro, brandstaetter
 */
public class FannShortcut extends Fann {

    public FannShortcut(List<Layer> layers) {

        super();

        if (layers == null)
            throw new IllegalArgumentException("layers == null");
        if (layers.isEmpty())
            throw new IllegalArgumentException("layers is empty");

        int[] neurons = new int[layers.size()];
        for (int x = 0; x < neurons.length; x++)
            neurons[x] = layers.get(x).size();

        ann = fann_create_shortcut_array(neurons.length, neurons);
        addLayers(layers);
    }

    /**
     * Create a new ANN with just the input and output layers for Cascade
     * Training
     * 
     * @param inputs
     *            The number of input neurons
     * @param outputs
     *            The number of output neurons
     */
    public FannShortcut(int inputs, int outputs) {
        int[] layers = new int[2];
        layers[0] = inputs;
        layers[1] = outputs;
        int numLayers = 2;
        ann = fann_create_shortcut_array(numLayers, layers);
    }
}
