
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;

public class UITile {

    int x, y;
    UITile up, right, down, left;
    FloorUI ui;
    javax.swing.JLabel contentLabel;
    javax.swing.JLabel upWallLabel, rightWallLabel, downWallLabel, leftWallLabel;
    boolean upWall, rightWall, downWall, leftWall;
    UITile thisTile = this;

    UITile(int i, int j, FloorUI ui) {
        this.x = i;
        this.y = j;
        this.ui = ui;
        upWall = i == 0;
        rightWall = j == 9;
        leftWall = j == 0;
        downWall = i == 9;
        upWallLabel = new javax.swing.JLabel();
        rightWallLabel = new javax.swing.JLabel();
        downWallLabel = new javax.swing.JLabel();
        leftWallLabel = new javax.swing.JLabel();
        contentLabel = new javax.swing.JLabel();
        upWallLabel.setOpaque(true);
        rightWallLabel.setOpaque(true);
        downWallLabel.setOpaque(true);
        leftWallLabel.setOpaque(true);
        contentLabel.setOpaque(true);
        contentLabel.setBackground(Color.black);
        contentLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int leftOrRight = e.getButton();
                if (leftOrRight == 3) {
                    if (ui.goal == thisTile) {
                        contentLabel.setIcon(null);
                        contentLabel.setHorizontalAlignment(0);
                        ui.goal = null;
                    } else if (ui.goal == null) {
                        contentLabel.setIcon(ui.batteryIcon);
                        ui.goal = thisTile;
                    }
                } else {
                    if (ui.start == thisTile) {
                        contentLabel.setIcon(null);
                        ui.start = null;
                    } else if (ui.start == null) {
                        contentLabel.setIcon(ui.robotIcon);
                        ui.start = thisTile;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        if (upWall) {
            upWallLabel.setBackground(Color.white);
        } else {
            upWallLabel.setBackground(Color.black);
        }
        if (rightWall) {
            rightWallLabel.setBackground(Color.white);
        } else {
            rightWallLabel.setBackground(Color.black);
        }
        if (downWall) {
            downWallLabel.setBackground(Color.white);
        } else {
            downWallLabel.setBackground(Color.black);
        }
        if (leftWall) {
            leftWallLabel.setBackground(Color.white);
        } else {
            leftWallLabel.setBackground(Color.black);
        }
        if (!upWall) {
            upWallLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (up != null) {
                        up.downWall = !up.downWall;
                        if (up.downWall) {
                            up.downWallLabel.setBackground(Color.white);
                        } else {
                            up.downWallLabel.setBackground(Color.black);
                        }
                    }
                    upWall = !upWall;
                    if (upWall) {
                        upWallLabel.setBackground(Color.white);
                    } else {
                        upWallLabel.setBackground(Color.black);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
        if (!rightWall) {
            rightWallLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (right != null) {
                        right.leftWall = !right.leftWall;
                        if (right.leftWall) {
                            right.leftWallLabel.setBackground(Color.white);
                        } else {
                            right.leftWallLabel.setBackground(Color.black);
                        }
                    }
                    rightWall = !rightWall;
                    if (rightWall) {
                        rightWallLabel.setBackground(Color.white);
                    } else {
                        rightWallLabel.setBackground(Color.black);
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
        if (!downWall) {
            downWallLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (down != null) {
                        down.upWall = !down.upWall;
                        if (down.upWall) {
                            down.upWallLabel.setBackground(Color.white);
                        } else {
                            down.upWallLabel.setBackground(Color.black);
                        }
                    }
                    downWall = !downWall;
                    if (downWall) {
                        downWallLabel.setBackground(Color.white);
                    } else {
                        downWallLabel.setBackground(Color.black);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
        if (!leftWall) {
            leftWallLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    {
                        if (left != null) {
                            left.rightWall = !left.rightWall;
                            if (left.rightWall) {
                                left.rightWallLabel.setBackground(Color.white);
                            } else {
                                left.rightWallLabel.setBackground(Color.black);
                            }
                        }
                        leftWall = !leftWall;
                        if (leftWall) {
                            leftWallLabel.setBackground(Color.white);
                        } else {
                            leftWallLabel.setBackground(Color.black);
                        }

                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
    }

    String walls() {
        return (upWall ? "1" : "0") + (rightWall ? "1" : "0") + (downWall ? "1" : "0") + (leftWall ? "1" : "0");
    }

    public String toString() {
        return x + ":" + y;
    }
}
