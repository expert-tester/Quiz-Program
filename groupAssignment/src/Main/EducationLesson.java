/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.Font;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;

/**
 *
 * @author Hui En
 */
public class EducationLesson {

    public static void buildLesson() {
        String[] lessons = {
            "1. Carpooling serves as a straightforward and impactful method to diminish your carbon footprint during commutes. By sharing rides with others, you actively decrease the quantity of vehicles on the road, thereby minimizing the release of carbon emissions into the atmosphere.",
            "2. As temperatures increase, heightened evaporation leads to intensified extreme rainfall and flooding, amplifying the impact of severe storms. The warming of the ocean directly influences both the frequency and intensity of tropical storms. Cyclones, hurricanes, and typhoons thrive on the warmth of the ocean's surface waters.",
            "3. Pollutant emissions into the atmosphere can bring about alterations in the climate. These pollutants, such as greenhouse gases, are commonly known as climate influencers. Ozone present in the atmosphere contributes to climate warming, whereas various elements within particulate matter can induce either warming or cooling effects on the climate.",
            "4. Burning fossil fuels results in the substantial release of carbon dioxide, a greenhouse gas, into the atmosphere. These gases trap heat, leading to global warming. The average global temperature has already risen by 1 degree Celsius as a result.",
            "5. The greenhouse gases within Earth's atmosphere retain heat, thereby raising the planet's temperature. Primary gases contributing to this greenhouse effect comprise carbon dioxide, methane, nitrous oxide, and water vapor. Alongside these natural elements, artificial fluorinated gases also act as greenhouse gases.",
            "6. The ultimate goal of the three agreements within the UNFCCC(United Nations Framework Convention on Climate Change) is to stabilize the concentrations of greenhouse gases in the atmosphere at a level that prevents harmful human interference with the climate system. This objective should be achieved within a timeframe that permits ecosystems to adapt naturally and fosters sustainable development."
        };

        for (int i = 0; i < lessons.length; i++) {
            String lessonDir = "text/lessons/lesson" + (i + 1) + ".txt";
            FileHandling.setFileContent(lessons[i], lessonDir, false);
        }

    }

    public static JTextArea showLesson(int current) {
        final String FILE_FORMAT = "text/lessons/lesson%d.txt";
        String filePath = String.format(FILE_FORMAT, current);
        JTextArea textArea = FileHandling.textToJTextArea(filePath);
        textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        textArea.setFont(new Font("Boulder", Font.TYPE1_FONT, 24)); 
        return textArea;
    }

    public static int maxLesson() {
        File directory = new File("text/lessons/");
        int lessonCount = directory.list().length;
        return lessonCount;
    }
    
}
