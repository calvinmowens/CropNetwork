package main.java;
import main.controllers.MainUiController;

public class IrrigationFarmer implements IFarmer {
    private int assignedPlotId;
    @Override
    public void changeAssignedCropPlot(int plotId) {
        assignedPlotId = plotId;
    }
    public void startWorking() {
        this.increaseWaterlevels();
    }
    public void increaseWaterlevels() {
        Game myGame = MainUiController.getGame();
        CropPlot[] myPlots = myGame.getPlots();
        CropPlot assignedPlot = myPlots[assignedPlotId];
        if (assignedPlot.getWaterLevel() < 2) {
            assignedPlot.setWaterLevel(3);
        }
    }
}

