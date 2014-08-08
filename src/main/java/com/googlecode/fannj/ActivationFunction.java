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

public enum ActivationFunction {

    /* The order these appear must match the order in the FANN src! */
    FANN_LINEAR, FANN_THRESHOLD, FANN_THRESHOLD_SYMMETRIC, FANN_SIGMOID, FANN_SIGMOID_STEPWISE,
    FANN_SIGMOID_SYMMETRIC, FANN_SIGMOID_SYMMETRIC_STEPWISE, FANN_GAUSSIAN, FANN_GAUSSIAN_SYMMETRIC,
    /*
     * Stepwise linear approximation to gaussian. Faster than gaussian but a bit
     * less precise. NOT implemented yet.
     */
    FANN_GAUSSIAN_STEPWISE, FANN_ELLIOT, FANN_ELLIOT_SYMMETRIC, FANN_LINEAR_PIECE,
    FANN_LINEAR_PIECE_SYMMETRIC, FANN_SIN_SYMMETRIC, FANN_COS_SYMMETRIC, FANN_SIN, FANN_COS
}
