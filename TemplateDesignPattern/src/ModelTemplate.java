public abstract class ModelTemplate {
    protected abstract void train();
    protected abstract void preprocess();
    protected abstract void evaluateModel();
    protected void loadData(String data){
        System.out.println("Loading data: "+data );
    }
    protected void saveModel(){
        System.out.println("SAVING MODEL: ");
    }

    public void templateMethod(String data){
        loadData(data);
        preprocess();
        train();
        evaluateModel();
        saveModel();
    }

}
