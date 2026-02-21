public class NeuralNetworkModel extends ModelTemplate{

    @Override
    protected void train() {
        System.out.println("Training neural network");
    }

    @Override
    protected void preprocess() {
        System.out.println("Preprocessing neural network");
    }

    @Override
    protected void evaluateModel() {
        System.out.println("Evaluating neural network");
    }
}
