/*
GanttProject is an opensource project management tool.
Copyright (C) 2005-2011 GanttProject Team

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
package net.sourceforge.ganttproject.action;

/**
 * Default OK/confirm action for dialogs.
 * {@link UIFacade#createDialog(java.awt.Component, javax.swing.Action[], String)}
 * adds additional/special functionalities for this action
 */

//Diogo Dias
public abstract class MailAction extends GPAction {
  private boolean isDefault = true;

  public MailAction() {
    this("send Mail");
  }

  public MailAction(String key) {
    super(key);
  }

  public boolean isDefault() {
    return isDefault;
  }

  protected void setDefault(boolean value) {
    isDefault = value;
  }
}
