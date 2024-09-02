import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Task class representing a task
class Task {
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

// TaskManager class to manage the tasks
class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

// Main application class with GUI
public class TodoApp {
    private JFrame frame;
    private TaskManager taskManager;
    private DefaultListModel<Task> listModel;

    public TodoApp() {
        taskManager = new TaskManager();
        listModel = new DefaultListModel<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("To-Do List Application");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JList<Task> taskList = new JList<>(listModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JTextField taskInput = new JTextField(20);
        panel.add(taskInput);

        JButton addButton = new JButton("Add Task");
        panel.add(addButton);

        JButton removeButton = new JButton("Remove Task");
        panel.add(removeButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskDescription = taskInput.getText();
                if (!taskDescription.isEmpty()) {
                    Task task = new Task(taskDescription);
                    taskManager.addTask(task);
                    listModel.addElement(task);
                    taskInput.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task selectedTask = taskList.getSelectedValue();
                if (selectedTask != null) {
                    taskManager.removeTask(selectedTask);
                    listModel.removeElement(selectedTask);
                }
            }
        });

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoApp());
    }
}
