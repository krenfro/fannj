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
 * A standard backpropagation neural network, which is not fully connected.
 * </p>
 * 
 * @author krenfro
 */
public class FannSparse extends Fann {

    public static final float DEFAULT_CONNECTION_RATE = 1f;
    float connectionRate = 1f;

    public FannSparse(List<Layer> layers) {
        this(DEFAULT_CONNECTION_RATE, layers);
    }

    public FannSparse(float connectionRate, List<Layer> layers) {

        super();

        if (layers == null)
            throw new IllegalArgumentException("layers == null");
        if (layers.isEmpty())
            throw new IllegalArgumentException("layers is empty");

        this.connectionRate = connectionRate;
        int[] neurons = new int[layers.size()];
        for (int x = 0; x < neurons.length; x++)
            neurons[x] = layers.get(x).size();

        ann = fann_create_sparse_array(connectionRate, neurons.length, neurons);
        addLayers(layers);
    }
}
