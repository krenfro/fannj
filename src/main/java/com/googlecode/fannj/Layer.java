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

import java.util.ArrayList;

/**
 * A Layer of Neurons in an ANN.
 * 
 * @author krenfro
 */
public class Layer extends ArrayList<Neuron> {

    private static final long serialVersionUID = -6467294440860703773L;

    /**
     * Create a Layer with the specified number of neurons with the default
     * Activation Function: {@link Neuron#DEFAULT_ACTIVATION_FUNCTION} with
     * steepness: {@link Neuron#DEFAULT_ACTIVATION_STEEPNESS}
     * 
     * @param numNeurons
     * @return
     */
    public static Layer create(int numNeurons) {
        return create(numNeurons, Neuron.DEFAULT_ACTIVATION_FUNCTION,
                      Neuron.DEFAULT_ACTIVATION_STEEPNESS);
    }

    /**
     * Create a Layer with the specified number of neurons and a particular
     * ActivationFunction with the steepness:
     * {@link Neuron#DEFAULT_ACTIVATION_STEEPNESS}
     * 
     * @param numNeurons
     * @param activationFunction
     * @return
     */
    public static Layer create(int numNeurons, ActivationFunction activationFunction) {

        return create(numNeurons, activationFunction, Neuron.DEFAULT_ACTIVATION_STEEPNESS);
    }

    /**
     * Create a Layer with the specified number of neurons and a particular
     * ActivationFunction with specified steepness
     * 
     * @param numNeurons
     * @param activationFunction
     * @param steepness
     * @return
     */
    public static Layer create(int numNeurons, ActivationFunction activationFunction,
                               float steepness) {

        Layer layer = new Layer();
        for (int i = 0; i < numNeurons; i++)
            layer.add(new Neuron(activationFunction, steepness));
        return layer;
    }
}
