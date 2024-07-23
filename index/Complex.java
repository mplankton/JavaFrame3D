package org.example.index;

public class Complex {
    float[] complex = new float[2];
    public Complex(float realPart, float imaginaryPart) {
        complex[0] = realPart;
        complex[1] = imaginaryPart;
    }
    public float complexFloat (boolean i) {
        float float_return;
        if (i) float_return = complex[0];
        else float_return = complex[1];
        return float_return;
    }
    public static Complex Plus (Complex z1, Complex z2) {
        return new Complex(z1.complexFloat(true) + z2.complexFloat(true), z1.complexFloat(false) + z2.complexFloat(false));
    }
    public static Complex Minus (Complex z1, Complex z2) {
        return new Complex(z1.complexFloat(true) - z2.complexFloat(true), z1.complexFloat(false) - z2.complexFloat(false));
    }
    public static Complex Multiplication (Complex z1, Complex z2) {
        float a = z1.complexFloat(true);
        float b = z1.complexFloat(false);
        float c = z2.complexFloat(true);
        float d = z2.complexFloat(false);
        return new Complex(a * c - b * d, a * d + b * c);
    }
    public static Complex Division (Complex z1, Complex z2) {
        float a = z1.complexFloat(true);
        float b = z1.complexFloat(false);
        float c = z2.complexFloat(true);
        float d = z2.complexFloat(false);
        return new Complex((a * c + b * d) / ((float)Math.pow(c, 2) + (float)Math.pow(d, 2)), (b * c - a * d) / ((float)Math.pow(c, 2) + (float)Math.pow(d, 2)));
    }
    public static Complex Pow2 (Complex z) {
        float a = z.complexFloat(true);
        float b = z.complexFloat(false);
        return new Complex((float) Math.pow(a, 2) - (float) Math.pow(b, 2), 2 * a * b);
    }
    public static Complex rotate (Complex posi, float z, float theta, float phi) {
        float x = posi.complexFloat(true);
        float y = posi.complexFloat(false);
        Complex posi1 = new Complex((float) (x * Math.cos(theta) - z * Math.sin(theta)), (float) (y * Math.sin(phi) - (x * Math.sin(theta) + z * Math.cos(theta)) * Math.cos(phi)));
        return posi1;
    }
}
