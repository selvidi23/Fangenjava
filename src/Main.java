import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;

// TODO: declare and define all needed enums, interfaces and classes
//
interface WingedFan
{
    public abstract void CreateFan(int sizeFan);
    public abstract void PrintFan();

}

interface WingFanFactory
{
    public abstract WingedFan CreateWingedFan(WingedFanFactoryImpl.WingedFanType type);

}

interface FangenIn
{
    public abstract void DoFangen() throws IOException;
}

class RightTypeFan implements WingedFan//-
{
    char Mtab[][];

    @Override
    public void CreateFan(int sizeFan)
    {
        Mtab = new char[2*sizeFan][2*sizeFan];
        int k,w;

        for(int i = 0; i < 2*sizeFan; i++)
            for(int j = 0; j < 2*sizeFan; j++) Mtab[i][j] = '.';

        for(int i = 0; i < 2*sizeFan; i++)
        {
            if(i < sizeFan)
            {
                k = sizeFan-i;
                w = sizeFan-1;
            }
            else
            {
                k = i-sizeFan+1;
                w = -sizeFan;
            }
            while(k!=0)
            {
                Mtab[i][Math.abs(w)] = '*';
                k--;
                w--;
            }
        }
        for(int i = 0; i < 2*sizeFan; i++)
        {
            if(i < sizeFan)
            {
                k = i+1;
                w = 2*sizeFan-1;
            }
            else
            {
                k = 2*sizeFan-i;
                w = 0;
            }
            while(k!=0)
            {
                Mtab[i][Math.abs(w)] = '*';
                k--;
                w--;
            }
        }
    }

    @Override
    public void PrintFan() {
        int size = Mtab.length;
        for(int i=0; i< size; i++){
            for(int j=0; j< Mtab[i].length; j++)
                System.out.print(Mtab[i][j]);
            System.out.println();
        }
    }
}

class  LeftTypeFan implements WingedFan//+
{


    char Mtab[][];

    @Override
    public void CreateFan(int sizeFan)
    {

        Mtab = new char[2*Math.abs(sizeFan)][2*Math.abs(sizeFan)];
        int k,w;

        for(int i = 0; i < 2*sizeFan; i++)
            for(int j = 0; j < 2*sizeFan; j++) Mtab[i][j] = '.';

            for(int i = 0; i < 2*sizeFan; i++)
            {
                if(i < sizeFan)
                {
                    k = i+1;
                    w = 0;
                }
                else
                {
                    k = 2*sizeFan - i;
                    w = -(2*sizeFan-1);
                }
                while(k!=0)
                {
                    Mtab[i][Math.abs(w)] = '*';
                    k--;
                    w++;
                }
            }
            for(int i = 0; i < 2*sizeFan; i++)
            {
                if(i < sizeFan)
                {
                    k = sizeFan-i;
                    w = sizeFan;
                }
                else
                {
                    k = i-sizeFan+1;
                    w = -sizeFan + 1;
                }
                while(k!=0)
                {
                    Mtab[i][Math.abs(w)] = '*';
                    k--;
                    w++;
                }
            }
        }


    @Override
    public void PrintFan() {
        int size = Mtab.length;
        for(int i=0; i< size; i++){
            for(int j=0; j< Mtab[i].length; j++)
                System.out.print(Mtab[i][j]);
            System.out.println();
        }
    }
}


class WingedFanFactoryImpl implements WingFanFactory
{
    enum WingedFanType
    {
        LeftTypeFan,RightTypeFan
    }

    @Override
    public WingedFan CreateWingedFan(WingedFanFactoryImpl.WingedFanType type) {
        switch (type)
        {
            case LeftTypeFan:
                return new LeftTypeFan();
            case RightTypeFan:
                return new RightTypeFan();
            default:
                throw new RuntimeException("Unsupported object type!");
        }

    }

}



class Fangen implements FangenIn
{

    String type= "";
    int sizeWingedFan = 0, temp = 0;
    private static WingFanFactory wingFanFactory = new WingedFanFactoryImpl();
    WingedFan Fan;

    @Override
    public void DoFangen() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            sizeWingedFan = Integer.parseInt(br.readLine());
            if(sizeWingedFan == 0)
            {
                break;
            }
            type = (sizeWingedFan < 0) ? "RightTypeFan" : "LeftTypeFan";
            temp = Math.abs(sizeWingedFan);
            Fan = wingFanFactory.CreateWingedFan(WingedFanFactoryImpl.WingedFanType.valueOf(type));
            Fan.CreateFan(temp);
            Fan.PrintFan();
        }

    }
}




class Main
{

    public static void main (String[] args) throws IOException {

        Fangen fangen = new Fangen();
        fangen.DoFangen();

    }
}			