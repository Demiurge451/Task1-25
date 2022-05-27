import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class TrafficLights2 {
    private int countSections;
    private int currentSeconds;
    private int timeChangeColor;
    private Color currentColor;
    private Timer timer;

    public TrafficLights2(int countSections, int timeChangeColor, Consumer<TrafficLights2> callback){
        this.countSections = countSections;
        this.timeChangeColor = timeChangeColor;
        TrafficLights2 t = this;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (recalcTime()){
                    callback.accept(t);
                }
            }
        }, 0, 1000);
    }

    /**
     * Проверяет была ли смена цвета
     * @return
     */
    private boolean recalcTime(){
        if (this.countSections == 3){
            return recalcTime3Sections();
        }
        if (this.countSections == 2){
            return recalcTime2Sections();
        }
        if (this.countSections == 4){
            return recalcTime4Sections();
        }
        return false;
    }

    /**
     * Проверяем была ли смена цвета у трехсекционного светофора
     * @return
     */
    private boolean recalcTime3Sections(){
        Color oldColor = currentColor;
        currentSeconds++;
        if (currentSeconds > timeChangeColor * 3){
            currentSeconds = 0;
        }
        if (currentSeconds < timeChangeColor){
            currentColor = Color.RED;
        }else if(currentSeconds < timeChangeColor * 2){
            currentColor = Color.YELLOW;
        }else{
            currentColor = Color.GREEN;
        }
        return oldColor != currentColor;
    }

    /**
     * Проверяет была ли смена цвета у двухсекционного светофора
     */
    private boolean recalcTime2Sections(){
        Color oldColor = currentColor;
        currentSeconds++;
        if (currentSeconds > timeChangeColor * 2){
            currentSeconds = 0;
        }
        if (currentSeconds < timeChangeColor){
            currentColor = Color.RED;
        }else{
            currentColor = Color.GREEN;
        }
        return oldColor != currentColor;
    }

    /**
     * Проверяет была ли смена цвета у четырехсекционного светофора
     */
    private boolean recalcTime4Sections(){
        Color oldColor = currentColor;
        currentSeconds++;
        if (currentSeconds > timeChangeColor *4){
            currentSeconds = 0;
        }
        if (currentSeconds < timeChangeColor){
            currentColor = Color.RED;
        }else if (currentSeconds < timeChangeColor * 2){
            currentColor = Color.YELLOW;
        }else if (currentSeconds < timeChangeColor * 3){
            currentColor = Color.GREEN;
        }else{
            currentColor = new Color(114, 255, 172);
        }
        return oldColor != currentColor;
    }

    /**
     * Возвращает текующий цвет светофора
     */
    public Color getCurrentColor() {return currentColor;}
}
