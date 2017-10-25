package pmp.pipes;

import pmp.interfaces.IOable;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

public class DoubleExitPushPipe<T> implements Writeable<T> {

    private Writeable<T> m_Output1 = null;
    private Writeable<T> m_Output2 = null;


    public DoubleExitPushPipe(Writeable<T> output1, Writeable<T> output2)   {
        if (output1 == null){
            throw new InvalidParameterException("output1 filter can't be null!");
        }
        if (output2 == null){
            throw new InvalidParameterException("output2 filter can't be null!");
        }
        m_Output1 = output1;
        m_Output2 = output2;
    }

    @Override
    public void write(T value) throws StreamCorruptedException {
        if ( m_Output1 == null )
            throw new InvalidParameterException("output filter can't be null!");

        if ( m_Output2 == null )
            throw new InvalidParameterException("output filter can't be null!");

        m_Output1.write(value);
        m_Output2.write(value);
    }
}
