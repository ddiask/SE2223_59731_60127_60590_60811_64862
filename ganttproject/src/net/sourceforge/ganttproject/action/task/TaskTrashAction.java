/*
GanttProject is an opensource project management tool. License: GPL3
Copyright (C) 2011 Dmitry Barashev

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.sourceforge.ganttproject.action.task;

import com.google.common.collect.Lists;
import net.sourceforge.ganttproject.GanttTree2;
import net.sourceforge.ganttproject.TreeUtil;
import net.sourceforge.ganttproject.action.GPAction;
import net.sourceforge.ganttproject.gui.UIFacade;
import net.sourceforge.ganttproject.gui.UIFacade.Choice;
import net.sourceforge.ganttproject.gui.UIUtil;
import net.sourceforge.ganttproject.task.Task;
import net.sourceforge.ganttproject.task.TaskManager;
import net.sourceforge.ganttproject.task.TaskNode;
import net.sourceforge.ganttproject.task.TaskSelectionManager;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import net.sourceforge.ganttproject.task.ResourceAssignment;


public class TaskTrashAction extends TaskActionBase {

  /**
   * The TaskTrashAction constructor
   * @param taskManager object that handles what happens to the different tasks
   * @param selectionManager gives the information about what task was selected
   * @param uiFacade the object that handles what happens in the screen
   * @param tree the object that handles the actions for tasks
   */
  public TaskTrashAction(TaskManager taskManager, TaskSelectionManager selectionManager, UIFacade uiFacade,
                         GanttTree2 tree) {
    super("task.trash", taskManager, selectionManager, uiFacade, tree);
  }

  /**
   * The TaskTrashAction constructor with size
   * @param taskManager object that handles what happens to the different tasks
   * @param selectionManager gives the information about what task was selected
   * @param uiFacade the object that handles what happens in the screen
   * @param tree the object that handles the actions for tasks
   * @param size the size of the icon
   */
  private TaskTrashAction(TaskManager taskManager, TaskSelectionManager selectionManager, UIFacade uiFacade,
                          GanttTree2 tree, IconSize size) {
    super("task.trash", taskManager, selectionManager, uiFacade, tree, size);
  }

  /**
   * Shows if the task is enabled
   * @param selection the list of tasks created
   * @return true because u should always be able to access the trash
   */
  @Override
  protected boolean isEnabled(List<Task> selection) {
    return true;
  }

  /**
   * The method that runs the trash action
   * @param selection the list of tasks created
   * @throws Exception
   */
  @Override
  protected void run(List<Task> selection) throws Exception {

    JFrame f = new JFrame();

    final DefaultListModel<Task> data = new DefaultListModel<>();
    final JList<Task> list = new JList<>(data);

    Task[] trash = getTaskManager().getTrash();
    for(int i = 0; i < trash.length; i++) {
      data.addElement(trash[i]);
    }

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    leftPanel.setLayout(new BorderLayout());
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    list.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

    leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    leftPanel.add(new JScrollPane(list));

    JButton delete = new JButton("Delete");
    JButton deleteall = new JButton("Delete All");
    JButton restore = new JButton("Restore");
    JButton restoreall = new JButton("Restore All");

    /**
     * The action when the delete button is clicked
     */
    delete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = 0;
        do {
          index = selmodel.getMinSelectionIndex();
          if (index >= 0) {
            getTaskManager().deleteTaskPermanently(data.get(index));
            data.remove(index);
          }
        } while(index >= 0);
      }
    });

    /**
     * The action when the deleteall button is clicked
     */
    deleteall.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getTaskManager().deleteTrashPermanently();
        data.clear();
      }
    });

    /**
     * The action when the restore button is clicked
     */
    restore.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = 0;
        do {
          index = selmodel.getMinSelectionIndex();
          if (index >= 0) {
            getTaskManager().restoreTask(data.get(index));
            data.remove(index);
          }
        } while(index >= 0);
      }
    });

    /**
     * The action when the restoreall button is clicked
     */
    restoreall.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getTaskManager().restoreAllTrash();
        data.clear();
      }
    });

    rightPanel.add(delete);
    rightPanel.add(deleteall);
    rightPanel.add(restore);
    rightPanel.add(restoreall);

    rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

    panel.add(leftPanel);
    panel.add(rightPanel);

    f.add(panel);

    f.setSize(350, 250);
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    Image icon = Toolkit.getDefaultToolkit().getImage("..\\..\\ganttproject\\data\\resources\\logos\\icon16.png");
    f.setIconImage(icon);
    f.setVisible(true);

    getUIFacade().getMainFrame().setEnabled(false);

    /**
     * When something happens to the window this method is activated and listens to the action
     */
    f.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        getUIFacade().getMainFrame().setEnabled(true);
      }
    });

  }

  /**
   * The method that transforms the action into a toolBarAction
   * @return the new TaskTrashAction
   */
  @Override
  public TaskTrashAction asToolbarAction() {
    TaskTrashAction result = new TaskTrashAction(getTaskManager(), getSelectionManager(), getUIFacade(), getTree());
    result.setFontAwesomeLabel(UIUtil.getFontawesomeLabel(result));
    return result;
  }
}