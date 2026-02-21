//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ModelTemplate neuralNetworkModel=new NeuralNetworkModel();
        neuralNetworkModel.templateMethod("/path/neuralNetwork");

        ModelTemplate decisionTreeModel=new DecisionTreeModel();
        decisionTreeModel.templateMethod("/path/decisionTree");
    }
}