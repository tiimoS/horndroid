
/*
 * MIT License
 *
 * Copyright (c) 2017 TU Wien
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.horndroid.util;


import com.microsoft.z3.BitVecExpr;
import org.jf.dexlib2.ValueType;
import org.jf.dexlib2.iface.value.BooleanEncodedValue;
import org.jf.dexlib2.iface.value.ByteEncodedValue;
import org.jf.dexlib2.iface.value.CharEncodedValue;
import org.jf.dexlib2.iface.value.DoubleEncodedValue;
import org.jf.dexlib2.iface.value.EncodedValue;
import org.jf.dexlib2.iface.value.EnumEncodedValue;
import org.jf.dexlib2.iface.value.FieldEncodedValue;
import org.jf.dexlib2.iface.value.FloatEncodedValue;
import org.jf.dexlib2.iface.value.IntEncodedValue;
import org.jf.dexlib2.iface.value.LongEncodedValue;
import org.jf.dexlib2.iface.value.ShortEncodedValue;
import org.jf.dexlib2.iface.value.StringEncodedValue;
import org.jf.dexlib2.util.ReferenceUtil;

import com.horndroid.z3.FSEngine;

public class FormatEncodedValue {
	
	 public static String toString(EncodedValue encodedValue, int size)  {
		 	long lVal;
	        switch (encodedValue.getValueType()) {
	            case ValueType.ANNOTATION:
	            	return "";
	            case ValueType.ARRAY:
	            	return "";
	            case ValueType.BOOLEAN:
	            	if (((BooleanEncodedValue)encodedValue).getValue()) {
	                    return Utils.hexDec64(1, size);
	                } else {
	                	return Utils.hexDec64(0, size);
	                }
	            case ValueType.BYTE:
	            	lVal = (long) ((ByteEncodedValue)encodedValue).getValue();
	             	return Utils.hexDec64(lVal, size);
	            case ValueType.CHAR:
	            	lVal = (long) ((CharEncodedValue)encodedValue).getValue();
	             	return Utils.hexDec64(lVal, size);
	            case ValueType.DOUBLE:
	            	lVal = Double.doubleToRawLongBits(((DoubleEncodedValue)encodedValue).getValue());
	            	return Utils.hexDec64(lVal, size);
	            case ValueType.ENUM:
	            	return Utils.hexDec64(ReferenceUtil.getShortFieldDescriptor(((EnumEncodedValue)encodedValue).getValue()).hashCode(), size);
	            case ValueType.FIELD:
	            	return Utils.hexDec64(ReferenceUtil.getShortFieldDescriptor(((FieldEncodedValue)encodedValue).getValue()).hashCode(), size);
	            case ValueType.FLOAT:
	            	lVal = (long) Float.floatToRawIntBits(((FloatEncodedValue)encodedValue).getValue());
	            	return Utils.hexDec64(lVal, size);
	            case ValueType.INT:
	            	return Utils.hexDec64((long)((IntEncodedValue)encodedValue).getValue(), size);
	            case ValueType.LONG:
	            	return Utils.hexDec64(((LongEncodedValue)encodedValue).getValue(), size);
	            case ValueType.METHOD:
	            	return "";
	            case ValueType.NULL:
	            	return Utils.hexDec64((long) 0, size);
	            case ValueType.SHORT:
	            	return Utils.hexDec64((long) ((ShortEncodedValue)encodedValue).getValue(), size);
	            case ValueType.STRING:
	            	return Utils.hexDec64(((StringEncodedValue)encodedValue).getValue().hashCode(), size);
	            case ValueType.TYPE:
	            	return "";
	        }
	        return "";
	    }

    public static BitVecExpr toBitVec(FSEngine fs, EncodedValue encodedValue, int size)  {
        long lVal;
        switch (encodedValue.getValueType()) {
            case ValueType.ANNOTATION:
            case ValueType.ARRAY:
                return fs.mkBitVector("", size);

            case ValueType.BOOLEAN:
                if (((BooleanEncodedValue)encodedValue).getValue()) {
                    return fs.mkBitVector(1, size);
                } else {
                    return fs.mkBitVector(0, size);
                }
            case ValueType.BYTE:
                lVal = (long) ((ByteEncodedValue)encodedValue).getValue();
                return fs.mkBitVector(lVal, size);
            case ValueType.CHAR:
                lVal = (long) ((CharEncodedValue)encodedValue).getValue();
                return fs.mkBitVector(lVal, size);
            case ValueType.DOUBLE:
                lVal = Double.doubleToRawLongBits(((DoubleEncodedValue)encodedValue).getValue());
                return fs.mkBitVector(lVal, size);
            case ValueType.ENUM:
                return fs.mkBitVector(ReferenceUtil.getShortFieldDescriptor(((EnumEncodedValue)encodedValue).getValue()).hashCode(), size);
            case ValueType.FIELD:
                return fs.mkBitVector(ReferenceUtil.getShortFieldDescriptor(((FieldEncodedValue)encodedValue).getValue()).hashCode(), size);
            case ValueType.FLOAT:
                lVal = (long) Float.floatToRawIntBits(((FloatEncodedValue)encodedValue).getValue());
                return fs.mkBitVector(lVal, size);
            case ValueType.INT:
                return fs.mkBitVector((long)((IntEncodedValue)encodedValue).getValue(), size);
            case ValueType.LONG:
                return fs.mkBitVector(((LongEncodedValue)encodedValue).getValue(), size);
            case ValueType.METHOD:
                return fs.mkBitVector("", size);
            case ValueType.NULL:
                return fs.mkBitVector((long) 0, size);
            case ValueType.SHORT:
                return fs.mkBitVector((long) ((ShortEncodedValue)encodedValue).getValue(), size);
            case ValueType.STRING:
                return fs.mkBitVector(((StringEncodedValue)encodedValue).getValue().hashCode(), size);
            case ValueType.TYPE:
                return fs.mkBitVector("", size);
        }
        return fs.mkBitVector("", size);
    }

}