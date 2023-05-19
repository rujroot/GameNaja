package equipment;

public class SkeletionBow extends Gun{

	public SkeletionBow() {
		super();
		this.setCooldownTime(1000);
	}

	@Override
    public Object clone() throws CloneNotSupportedException {
        Bow clone = (Bow) super.clone();
        return clone;
    }
}
