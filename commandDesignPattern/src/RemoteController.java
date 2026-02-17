import java.util.ArrayList;
import java.util.List;

public class RemoteController {
    private static final int totalButton=5;
    Command[] commandArray=new Command[totalButton];
    public void setCommand(Command c, int index){
        if(index>=0 && index<totalButton && commandArray[index]==null){
            commandArray[index]=c;
        }
        else{
            System.out.println("Command already set at this button, try another button");
        }
    }

    public void pressButton(int index){
        if(index>=0 && index<totalButton && commandArray[index]!=null){
                commandArray[index].execute();
        }
        else{
            System.out.println("no command assigned at that button");
        }
    }
}
