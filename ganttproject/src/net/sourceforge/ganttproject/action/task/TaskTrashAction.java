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

public class TaskTrashAction extends TaskActionBase {

  public TaskTrashAction(TaskManager taskManager, TaskSelectionManager selectionManager, UIFacade uiFacade,
                         GanttTree2 tree) {
    super("task.trash", taskManager, selectionManager, uiFacade, tree);
  }

  private TaskTrashAction(TaskManager taskManager, TaskSelectionManager selectionManager, UIFacade uiFacade,
                          GanttTree2 tree, IconSize size) {
    super("task.trash", taskManager, selectionManager, uiFacade, tree, size);
  }

  @Override
  public GPAction withIcon(IconSize size) {
    return new TaskTrashAction(getTaskManager(), getSelectionManager(), getUIFacade(), getTree(), size);
  }

  @Override
  protected boolean isEnabled(List<Task> selection) {
    return !selection.isEmpty();
  }

  @Override
  protected void run(List<Task> selection) throws Exception {
    final DefaultMutableTreeTableNode[] cdmtn = getTree().getSelectedNodes();
    Map<Integer, List<DefaultMutableTreeTableNode>> levelMap = new TreeMap<Integer, List<DefaultMutableTreeTableNode>>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        // descending order
        return o2.compareTo(o1);
      }
    });
    for (DefaultMutableTreeTableNode node : cdmtn) {
      int level = TreeUtil.getLevel(node);
      List<DefaultMutableTreeTableNode> levelList = levelMap.get(level);
      if (levelList == null) {
        levelList = Lists.newArrayList();
        levelMap.put(level, levelList);
      }
      levelList.add(node);
    }
    getTree().stopEditing();

    for (List<DefaultMutableTreeTableNode> levelList : levelMap.values()) {
      for (DefaultMutableTreeTableNode node : levelList) {
        if (node != null && node instanceof TaskNode) {
          Task task = (Task) node.getUserObject();
          //getTaskManager().deleteTask(task);
          System.out.println(getTaskManager().trash()[0]);
        }
      }
    }
    forwardScheduling();
  }

  @Override
  public TaskTrashAction asToolbarAction() {
    TaskTrashAction result = new TaskTrashAction(getTaskManager(), getSelectionManager(), getUIFacade(), getTree());
    return result;
  }
}
