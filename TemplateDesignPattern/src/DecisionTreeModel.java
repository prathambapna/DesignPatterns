public class DecisionTreeModel extends ModelTemplate{
    @Override
    protected void train() {
        System.out.println("Training decision_tree model");
    }

    @Override
    protected void preprocess() {
        System.out.println("Preprocessing decision_tree model");
    }

    @Override
    protected void evaluateModel() {
        System.out.println("Evaluating decision_tree model");
    }
}
