# Project Management
**Process Model:**
Our process model was generally fairly agile, without any formal structure and with an iterative, informal approach to planning and task delegation. Given that this is was a fairly small project, with a small team and over the course of only a couple weeks, we figured it was unecessary to use the extensive documentation or structure of a more plan-driven model. Nonetheless, the order in which we approached the project somewhat resembed a V-model: we first built individual panels and tested whether they worked in isolation; then, an arraylist to facilitate communication between panels and tested to make sure changes in one panel were being reflected in others; finally, we refined and tested the different components together as a complete application to verify that it resembled expectations and functioned as per requirements.

**Architecture Model:** Since this project revolves largely around the management and sharing of data between subsystems, repository architecture seemed like an appropriate model for us to use. We have a number of different subsystems, in this case panels, which each maintain their own database in the form of an array of weatherData objects, and they use arraylists to pass that data to other subsystems. 

**How were tasks split among team members?**
Tasks were split among team members informally, with people volunteering to work on different elements of the project as they came up. When issues with certain elements of the project prevented us from being able to move forward to further stages--like communication of data between different panels--multiple team members worked in parallel to find a solution; while only one person's solution would ultimately be implemented, having multiple people on it at once increased the odds that the isuse would be solved sooner rather than later, and these obstacles often made it difficult for those team members to work on other things anyway.

**How did you monitor progress?**
We used a shared github repository for project files. This allowed changes made by one team member to be easily viewable for the rest of the team, and frequent updates to the code allowed us all to be working from the same iteration of the project even as we were coding and testing on different machines. Github's documentation as well as comments within the code helped us to keep track of the progress made on different modules and the project as a whole without necessarily needing to pore over the actual code. Additionally, we used discord to communicate with each other as we worked and to notify the rest of the team of major changes or issues.

# Requirements
**Requirements Definition**
* need to develop a GUI application that gets and shows data about the temperature of various cities
* load data
  *  ask user for filepath to .csv file
  *  read the file
  *   load data into a data structure (designed by your team)
  *   display data in a table in the application
* add data: provide fields for user to manually enter the date and other information
  *   this data will be added to the table on the main page
*  save data: save everything in the table to a csv file
*  plot data: plot the data to show

**User Stories**
* As a user, I want to load data about city temperatures from a .csv file so I can see the data in a table or graph.
* As a user, I want to add an item to the data so I can see it in a table or graph in addition to any loaded data.
* As a user, I want to save data about city temperatures to a .csv file so I can use it elsewhere outside this application.
* As a user, I want to plot data about city temperatures in a graph so I can easily visualize it and make comparisons.
* As a user, I want to show data about city temperatures in a table so I can see all of the data laid out.
* As a user, I want to view the team number and names of the people who made this app.

# Class Diagram
```
@startuml

class MainPanel extends JPanel implements ActionListener{
    - cardLayout: CardLayout
    - buttonsPanel: SelectionButtons
    - buttonsGroup: ButtonGroup
    - defaultPanel: DefaultPanel
    - aboutPanel: JPanel
    - savePanel: JPanel
    - loadPanel: LoadDataPanel
    - addPanel: AddDataPanel
    - plotPanel: JPanel
    - csvParser: CSVParser
    MainPanel()
    + SelectionButtons getButtonPanel()
    + void actionPerformed(ActionEvent)
}

class MainWindow extends JFrame{
    - MainPanel: mainPanel
    MainWindow()
    + static void main(String[] args)
}

class PlotDataPanel extends JPanel{
    - chart: JFreeChart
    - panel: ChartPanel
    - plot: XYPlot
    PlotDataPanel()
    - XYDataset createDataset()
}

class SaveDataPanel extends JPanel{
    - selectFileLabel: JLabel
    - saveFileLabel: JTextField
    SaveDataPanel()
}

class SelectionButtons extends JPanel{
    - buttons: ArrayList<JButton>
    - buttonsGroup: ButtonGroup
    - boxLayout: BoxLayout

    SelectionButtons()
    + ButtonGroup getButtonGroup()
    + void addActionListeners(ActionListener)
    + void addButton(String)
    + JButton getSelected()
}

class DefaultPanel extends JPanel {
    - tabelModel: DefaultTableModel
    - tabel: JTable
    - scrollPane: JScrollPane
    DefaultPanel()
    + void frontEnd()
    + void addRow()
}

class LoadDataPanel extends JPanel {
    - selectFileLabel: JLabel
    - loadFileField: JTextField
    LoadDataPanel()
    + void frontEnd()
}

class WeatherData {
    - cityName: String
    - date: String
    - temperature: double
    - typeDegree chart
    weatherData(String, double, char)
    + String getCityName()
    + double getTemperature()
    + char getTypeDegree()
}

class AboutPanel extends JPanel {
    - aboutLabel: JLabel
    AboutPanel()
}

class AddDataPanel extends JPanel {
    - csvParser CSVParser
    AddDataPanel()
    + void frontEnd()
}

class CSVParser {
    - fileName: String
    - splitLine: String[]
    - rootFolder: String
    + static weatherDataArrayList: ArrayList<weatherData>
    + static defaultPanel: DefaultPanel
    CSVParser(DefaultPanel)
    CSVParser()
    + void parseReadFile(String)
    + void addWeatherArray(String, String, double, char)
    + void parseSaveFile(String)
    + String[] uniqueCities()
    + double toFahrenheit(double, char)
    + ArrayList<String>[] createDatasetTime()
    + ArrayList<Double>[] createDatasetTemp()
    + Day dateFormat(String)
}

MainWindow *-- MainPanel
MainPanel *-- PlotDataPanel
MainPanel *-- DefaultPanel
MainPanel *-- LoadDataPanel
MainPanel *-- AddDataPanel
MainPanel *-- SaveDataPanel
MainPanel *-- CSVParser
MainPanel *-- SelectionButtons
MainPanel *-- AboutPanel
AddDataPanel *-- CSVParser
WeatherData *-- CSVParser

@enduml
```

![image](https://user-images.githubusercontent.com/29449588/124693057-99fd3880-de93-11eb-9547-eea522524ab8.png)

# Program Execution
![Screenshot 2021-07-06 193147](https://user-images.githubusercontent.com/29449588/124693145-c3b65f80-de93-11eb-8f16-f0372333a2b3.jpg)
![Screenshot 2021-07-06 193314](https://user-images.githubusercontent.com/29449588/124693150-c6b15000-de93-11eb-844b-f4ca8a4861da.jpg)
![Screenshot 2021-07-06 193453](https://user-images.githubusercontent.com/29449588/124693160-cadd6d80-de93-11eb-9276-1938e566e28b.jpg)
![Screenshot 2021-07-06 193518](https://user-images.githubusercontent.com/29449588/124693164-cd3fc780-de93-11eb-861f-7a4d36f0b00a.jpg)
![Screenshot 2021-07-06 193649](https://user-images.githubusercontent.com/29449588/124693171-d03ab800-de93-11eb-8685-24c395d78190.jpg)
![Screenshot 2021-07-06 193707](https://user-images.githubusercontent.com/29449588/124693186-d5980280-de93-11eb-9e28-8df420414621.jpg)
![Screenshot 2021-07-06 195215](https://user-images.githubusercontent.com/29449588/124693193-d892f300-de93-11eb-9922-6da305beccd1.jpg)
![Screenshot 2021-07-06 193835](https://user-images.githubusercontent.com/29449588/124693199-daf54d00-de93-11eb-9399-e299d66a711b.jpg)
![image](https://user-images.githubusercontent.com/29449588/124697149-2a8b4700-de9b-11eb-9373-70260da45f81.png)





# Testing
Testing occured constantly during the development process with a final push towards the end. The program was constantly run while coding-after each iteration, members made sure the added functionality worked with the expected data before moving on to dependant parts. Following the completion of the process of adding each function, incorrect data was input to force errors and exceptions to be appropriately handled.
Here are a few of the errors found at the end when giving the program unexpected input.

![Screenshot 2021-07-06 212022](https://user-images.githubusercontent.com/29449588/124702122-b655a100-dea4-11eb-93b4-bafd1ef386bf.jpg)
![Screenshot 2021-07-06 212706](https://user-images.githubusercontent.com/29449588/124702130-bc4b8200-dea4-11eb-8017-d41b4542eb42.jpg)
![Screenshot 2021-07-06 212900](https://user-images.githubusercontent.com/29449588/124702135-be154580-dea4-11eb-908f-23f747b26dad.jpg)
![Screenshot_20210707_010859](https://user-images.githubusercontent.com/85513745/124704002-33cee080-dea8-11eb-8b49-66a157f673d1.png)
![Screenshot_20210707_011419](https://user-images.githubusercontent.com/85513745/124704060-49dca100-dea8-11eb-947d-18392314e47e.png)
![Screenshot_20210707_011457](https://user-images.githubusercontent.com/85513745/124704067-4e08be80-dea8-11eb-93cb-16ac70eb84a4.png)


