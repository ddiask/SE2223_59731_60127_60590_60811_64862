package net.sourceforge.ganttproject.task;
import biz.ganttproject.core.calendar.AlwaysWorkingTimeCalendarImpl;
import biz.ganttproject.core.calendar.GPCalendarCalc;
import biz.ganttproject.core.option.ColorOption;
import biz.ganttproject.core.time.TimeUnitStack;
import biz.ganttproject.core.time.impl.GPTimeUnitStack;
import junit.framework.TestCase;
import net.sourceforge.ganttproject.GanttProject;
import net.sourceforge.ganttproject.GanttTree2;
import net.sourceforge.ganttproject.TaskContainmentHierarchyFacadeImpl;
import net.sourceforge.ganttproject.gui.NotificationManager;
import net.sourceforge.ganttproject.resource.HumanResource;
import net.sourceforge.ganttproject.resource.HumanResourceManager;
import net.sourceforge.ganttproject.roles.RoleManager;
import net.sourceforge.ganttproject.task.*;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TrashTest extends TestCase {

    private HumanResourceManager resourceManager;

    public TrashTest() {
        super();
        resourceManager = new HumanResourceManager(RoleManager.Access.getInstance().getDefaultRole(), null);
    }

    public void testStartsWithNoTasks() {
        TaskManager manager = newTaskManager();
        assert (manager.getTaskMap().getTasks().length == 0);
    }

    public void testStartsWithNoTrash() {
        TaskManager manager = newTaskManager();
        assert(manager.getTaskMap().getGarbageTasks().length == 0);
    }

    public void testCanCreateTasks() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.createTask();
        manager.createTask();
        assert(manager.getTaskMap().getTasks().length == 3);
    }

    public void testCanRemoveTasks() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        assert(manager.getTaskMap().getTasks().length == 0);
    }

    public void testAddsToGarbage() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        assert(manager.getTaskMap().getGarbageTasks().length == 1);
    }

    public void testCorrectTaskInGarbage() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        assert(manager.getTaskMap().getGarbageTasks()[0].getTaskID() == 0);
    }

    public void testPermenantlyDeleteSingleTask() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.deleteTaskPermanently(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getGarbageTasks().length == 0);
    }

    public void testPermenantlyDeletedTaskNotFound() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.deleteTaskPermanently(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks().length == 0);
    }

    public void testPermenantlyDeleteMultipleTasks() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.createTask();
        Task[] tasks = manager.getTaskMap().getTasks();
        manager.removeTask(tasks[0]);
        manager.removeTask(tasks[1]);
        manager.deleteTrashPermanently();
        assert(manager.getTaskMap().getGarbageTasks().length == 0);
    }

    public void testPermenantlyDeletedMultipleTasksNotFound() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.createTask();
        Task[] tasks = manager.getTaskMap().getTasks();
        manager.removeTask(tasks[0]);
        manager.removeTask(tasks[1]);
        manager.deleteTrashPermanently();
        assert(manager.getTaskMap().getTasks().length == 0);
    }

    public void testCanRestoreSingleTask() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getGarbageTasks().length == 0);
    }

    public void testRestoredTaskGoesToTasks() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks().length == 1);
    }

    public void testCanRestoreMultipleTasks() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.createTask();
        Task[] tasks = manager.getTaskMap().getTasks();
        manager.removeTask(tasks[0]);
        manager.removeTask(tasks[1]);
        manager.restoreAllTrash();
        assert(manager.getTaskMap().getGarbageTasks().length == 0);
    }

    public void testRestoredMultipleTasksGoToTasks() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.createTask();
        Task[] tasks = manager.getTaskMap().getTasks();
        manager.removeTask(tasks[0]);
        manager.removeTask(tasks[1]);
        manager.restoreAllTrash();
        assert(manager.getTaskMap().getTasks().length == 2);
    }

    public void testRestoreTaskHasSameName() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.getTaskMap().getTasks()[0].setName("taskname");
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks()[0].getName().equals("taskname"));
    }

    public void testRestoreTaskHasSameColor() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.getTaskMap().getTasks()[0].setColor(new Color(255, 0, 0));
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks()[0].getColor().equals(new Color(255, 0, 0)));
    }

    public void testRestoreTaskHasSameWebLink() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.getTaskMap().getTasks()[0].setWebLink("https://www.google.pt/?hl=pt-PT");
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks()[0].getWebLink().equals("https://www.google.pt/?hl=pt-PT"));
    }

    public void testRestoreTaskHasSameNote() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.getTaskMap().getTasks()[0].setNotes("My notes");
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks()[0].getNotes().equals("My notes"));
    }

    public void testRestoreTaskHasSameCompletion() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.getTaskMap().getTasks()[0].setCompletionPercentage(88);
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks()[0].getCompletionPercentage() == 88);
    }

    public void testRestoreTaskIsMilestone() {
        TaskManager manager = newTaskManager();
        manager.createTask();
        manager.getTaskMap().getTasks()[0].setMilestone(true);
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks()[0].isMilestone() == true);
    }

    public void testRestoreHumanResource() {
        TaskManager manager = newTaskManager();
        HumanResource resource = getResourceManager().create("human", 0);
        manager.createTask();
        manager.getTaskMap().getTasks()[0].addHumanResource(getResourceManager().getById(0));
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks()[0].getAssignments()[0].getResource().getName() == resource.getName());
    }

    public void testRestoreMultipleHumanResources() {
        TaskManager manager = newTaskManager();
        HumanResource resource1 = getResourceManager().create("human0", 0);
        HumanResource resource2 = getResourceManager().create("human1", 1);
        manager.createTask();
        manager.getTaskMap().getTasks()[0].addHumanResource(getResourceManager().getById(0));
        manager.getTaskMap().getTasks()[0].addHumanResource(getResourceManager().getById(1));
        manager.removeTask(manager.getTaskMap().getTasks()[0]);
        manager.restoreTask(manager.getTaskMap().getGarbageTasks()[0]);
        assert(manager.getTaskMap().getTasks()[0].getAssignments().length == 2);
    }

    private TaskManager newTaskManager() {
        return TaskManager.Access.newInstance(null, new TaskManagerConfig() {

            @Override
            public Color getDefaultColor() {
                return null;
            }

            @Override
            public ColorOption getDefaultColorOption() {
                return null;
            }

            @Override
            public GPCalendarCalc getCalendar() {
                return new AlwaysWorkingTimeCalendarImpl();
            }

            @Override
            public TimeUnitStack getTimeUnitStack() {
                return new GPTimeUnitStack();
            }

            @Override
            public HumanResourceManager getResourceManager() {
                return null;
            }

            @Override
            public URL getProjectDocumentURL() {
                return null;
            }

            @Override
            public NotificationManager getNotificationManager() {
                return null;
            }
        });
    }

    private HumanResourceManager getResourceManager() {
        return resourceManager;
    }

}