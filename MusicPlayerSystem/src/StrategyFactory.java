public class StrategyFactory {
    public PlayStrategy getStrategy(StrategyType strategyType, Playlist playlist){
        if(strategyType== StrategyType.SEQUENTIAL){
             return new SequentialStrategy(playlist);
        }
        else if(strategyType==StrategyType.RANDOM){
             return new RandomStrategy(playlist);
        }
        return null;
    }
}
