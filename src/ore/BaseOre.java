package ore;

import Data.BaseObject;
import Data.DataOre;
import Data.Point;

public abstract class BaseOre extends BaseObject  {
    private DataOre dataOre;

    public BaseOre(Point position, double width, double height, DataOre dataOre){
        this.setPosition(position);
        this.setDataOre(dataOre); 
    }

    public DataOre getDataOre() {
        return dataOre;
    }

    public void setDataOre(DataOre dataOre) {
        this.dataOre = dataOre;
    }

}
