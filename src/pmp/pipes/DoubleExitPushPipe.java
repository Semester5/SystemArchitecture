package pmp.pipes;

import pmp.interfaces.IOable;
import pmp.interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class DoubleExitPushPipe<T> implements Writeable<ArrayList<String>> {

    private Writeable<ArrayList<String>> m_Output1 = null;
    private Writeable<ArrayList<String>> m_Output2 = null;


    public DoubleExitPushPipe(Writeable<ArrayList<String>> output1, Writeable<ArrayList<String>> output2)   {
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
    public void write(ArrayList<String> value) throws StreamCorruptedException {
        if ( m_Output1 == null )
            throw new InvalidParameterException("output filter can't be null!");

        if ( m_Output2 == null )
            throw new InvalidParameterException("output filter can't be null!");

        m_Output1.write(value);
        m_Output2.write(value);
    }
}
