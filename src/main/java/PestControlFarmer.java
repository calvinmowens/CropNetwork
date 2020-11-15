package main.java;
import main.controllers.MainUiController;

public class PestControlFarmer implements IFarmer {
    private int assignedPlotId;
    private String name;

    @Override
    public void changeAssignedCropPlot(int plotId) {
        assignedPlotId = plotId;
    }

    @Override
    public void startWorking() {
        this.applyPesticide();
    }

    public void applyPesticide() {
        Game myGame = MainUiController.getGame();
        CropPlot[] myPlots = myGame.getPlots();
        CropPlot assignedPlot = myPlots[assignedPlotId];
        if (!assignedPlot.isPestApplied()) {
            assignedPlot.setPestApplied(true);
        }
    }
}

