package snakeGame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class GamePanel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten = bodyParts;
	int applex;
	int appley;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	GamePanel(){
		random = new Random();
	    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT + 60)); 
	    this.setBackground(Color.black);
	    this.setFocusable(true);
	    this.addKeyListener(new MyKeyAdapter());
	    startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    if(running) {
	        // Header
	        g2d.setColor(new Color(15, 15, 15)); 
	        g2d.fillRect(0, 0, SCREEN_WIDTH, 60); 
	        g2d.setColor(new Color(255, 215, 0)); 
	        g2d.setStroke(new BasicStroke(3)); 
	        g2d.drawLine(0, 60, SCREEN_WIDTH, 60);

	        // Score
	        g2d.setFont(new Font("Consolas", Font.BOLD, 30));
	        g2d.drawString("Score: " + applesEaten, (SCREEN_WIDTH / 2) - 120, 42);

	        // Reset Button
	        if (this.getComponentCount() == 0) {
	            JButton resetBtn = new JButton("Reset");
	            resetBtn.setFont(new Font("Consolas", Font.BOLD, 16));
	            resetBtn.setBackground(Color.BLACK);
	            resetBtn.setForeground(Color.WHITE);
	            resetBtn.setFocusable(false);
	            resetBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 1));
	            resetBtn.setBounds(SCREEN_WIDTH - 110, 15, 90, 30);
	            
	            resetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
	                public void mouseEntered(java.awt.event.MouseEvent evt) {
	                    resetBtn.setBackground(Color.WHITE);
	                    resetBtn.setForeground(Color.BLACK);
	                }
	                public void mouseExited(java.awt.event.MouseEvent evt) {
	                    resetBtn.setBackground(Color.BLACK);
	                    resetBtn.setForeground(Color.WHITE);
	                }
	            });
	            resetBtn.addActionListener(e -> gamePanel1());
	            this.setLayout(null);
	            this.add(resetBtn);
	        }

	        // Drawing the game
	        // Apple 
	        g2d.setColor(Color.red);
	        g2d.fillOval(applex, appley + 60, UNIT_SIZE, UNIT_SIZE);

	        // Snake
	        for(int i = 0; i < bodyParts ; i++) {
	            if(i == 0) { // head and eyes
	                g2d.setColor(new Color(0, 255, 100));
	                g2d.fillRoundRect(x[i], y[i] + 60, UNIT_SIZE, UNIT_SIZE, 15, 15);
	                
	                // eyes
	                g2d.setColor(Color.white);
	                g2d.fillOval(x[i] + 4, y[i] + 64, 6, 6);
	                g2d.fillOval(x[i] + 14, y[i] + 64, 6, 6);
	                // pupils
	                g2d.setColor(Color.black);
	                g2d.fillOval(x[i] + 6, y[i] + 66, 3, 3);
	                g2d.fillOval(x[i] + 16, y[i] + 66, 3, 3);
	            } else { // body
	                int transparency = Math.max(255 - (i * 3), 100);
	                g2d.setColor(new Color(0, 150, 50, transparency));
	                g2d.fillRoundRect(x[i], y[i] + 60, UNIT_SIZE, UNIT_SIZE, 10, 10);
	            }
	        }
	    } else {
	        gameOver(g2d);
	    }
	}
	
	public void newApple() {
		applex = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
		appley = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
	}
	
	public void move() {
		for(int i = bodyParts; i > 0 ; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch (direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		default:
			System.out.println("error in switch -- move founction --");
		}
	}
	
	public void checkApple() {
		if((x[0] == applex) && (y[0] == appley)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	
	public void checkCollisions() {
	    // body collision
	    for(int i = bodyParts; i > 0; i--) {
	        if((x[0] == x[i]) && (y[0] == y[i])) {
	            running = false;
	        }
	    }
	    
	    // left border collision
	    if(x[0] < 0) {
	    	running = false;
	    }
	    // right border collision
	    else if(x[0] >= SCREEN_WIDTH) {
	    	running = false;
	    }
	    
	    // top border collision (yellow line)
	    if(y[0] < 0) {
	    	running = false;
	    }
	    // bottom border collision
	    else if(y[0] >= SCREEN_HEIGHT) {
	    	running = false;
	    }

	    if(!running) {
	        timer.stop();
	    }
	}
	
	public void gameOver(Graphics g) {
	    this.removeAll(); 

	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    // 1. display "Final Score"
	    g2d.setColor(new Color(255, 215, 0)); 
	    g2d.setFont(new Font("Consolas", Font.BOLD, 45));
	    FontMetrics metrics1 = getFontMetrics(g2d.getFont());
	    g2d.drawString("Final Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Final Score: " + applesEaten))/2, 100);

	    // 2. display "Game Over"
	    String gameOverText = "GAME OVER";
	    g2d.setFont(new Font("Consolas", Font.BOLD, 85));
	    FontMetrics metrics2 = getFontMetrics(g2d.getFont());
	    
	    g2d.setColor(new Color(50, 50, 50));
	    g2d.drawString(gameOverText, (SCREEN_WIDTH - metrics2.stringWidth(gameOverText))/2 + 4, SCREEN_HEIGHT/2 + 4);
	    
	    g2d.setColor(new Color(220, 20, 60));
	    g2d.drawString(gameOverText, (SCREEN_WIDTH - metrics2.stringWidth(gameOverText))/2, SCREEN_HEIGHT/2);

	    // 3.  button design inside gameOver
	    if (this.getComponentCount() == 0) {
	        JButton btn = new JButton("PLAY AGAIN");
	        btn.setFont(new Font("Consolas", Font.BOLD, 22));
	        btn.setFocusPainted(false);
	        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        
	        // normal mode (before mouse over)
	        btn.setBackground(Color.BLACK);
	        btn.setForeground(Color.WHITE);
	        btn.setBorder(BorderFactory.createLineBorder(new Color(220, 20, 60), 2));
	        
	        // adding an effect Hover (Mouse over)
	        btn.addMouseListener(new java.awt.event.MouseAdapter() {
	            @Override
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                // what happens when you hover over the button: white background, black text
	                btn.setBackground(Color.WHITE);
	                btn.setForeground(Color.BLACK);
	            }

	            @Override
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                // what happens when the mouse leaves the button: returns to the original state
	                btn.setBackground(Color.BLACK);
	                btn.setForeground(Color.WHITE);
	            }
	        });

	        btn.setBounds((SCREEN_WIDTH - 180)/2, SCREEN_HEIGHT/2 + 80, 180, 50);
	        btn.addActionListener(e -> gamePanel1());
	        
	        this.setLayout(null);
	        this.add(btn);
	        this.revalidate();
	    }
	}
	public void gamePanel1() {
		
		// removing all the buttons we added so the screen is clean for the new game
	    this.removeAll(); 
	    
	    // reset game data
	    bodyParts = 6;
	    applesEaten = bodyParts;
	    direction = 'R';
	    
	    // reset the snake's position (important so it doesn't start from where it was disqualified)
	    for(int i = 0; i < bodyParts; i++) {
	        x[i] = 0;
	        y[i] = 0;
	    }
	    
	    // Restart
	    running = true;
	    timer.restart();
	    
	    // Screen update
	    repaint();
	    
	    // return focus to the keyboard (otherwise the snake won't move)
	    this.requestFocusInWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT: 
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT: 
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP: 
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN: 
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			default:
				System.out.println("error in switch -- MyKeyAdapter class --");
			}
		}
	}
}
