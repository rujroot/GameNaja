package ore;

import Data.BaseObject;
import Data.DataOre;
import Data.Point;

public abstract class BaseOre extends BaseObject  {
    private DataOre dataOre;

    public BaseOre(Point position, double width, double height, DataOre dataOre){
        super(position, width, height);
        this.setPosition(position);
        this.setDataOre(dataOre); 
    }

    public abstract void onBreak();

    public DataOre getDataOre() {
        return dataOre;
    }

    public void setDataOre(DataOre dataOre) {
        this.dataOre = dataOre;
    }

}
