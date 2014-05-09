
public class KillThemAllObjective extends Objective {

	protected boolean sentOrders = false;
	
	@Override
	public boolean perform(Phase phase, Agent owner) {
		
		if (phase == Phase.BUILD && !sentOrders) {
			sentOrders = true;
			for (Position b : Map.getOpponentBases()) {
				Objective o = new OccupyObjective(b);
				o.setPriority(10f);
				o.setSpendingLimit(Mothership.getInstance().getResources() / 4);
				Mothership.getInstance().addObjective(o);
			}
		}
		
		return true;
	}

}
