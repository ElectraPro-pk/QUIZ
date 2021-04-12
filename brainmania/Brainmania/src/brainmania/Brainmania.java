/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainmania;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mypc
 */
public class Brainmania extends Application {

    Stage Window;
    Scene home, host;
    String nick_name;
    final int interval = 1000;
    int total_sec = 60;
    int questions_remaining;
    long time_diff;
    String amount = "5", category = "21", type = "multiple";
    JSONArray questions_all = new JSONArray();
    String correct_Answer = "";
    String selected_Answer = "";
    int now = 0;
    int Right_Answers = 0;
    final int Point_per_question = 2;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Window = primaryStage;

        //home page
        //calling logo
        Image logo_home = new Image(new FileInputStream("images/BrainMania.png"));
        ImageView logo_home_view = new ImageView(logo_home);
        logo_home_view.setFitHeight(50);
        logo_home_view.setFitWidth(200);
        GridPane.setConstraints(logo_home_view, 0, 0);

        //Adding label
        Label name_lbl = new Label("Enter Nickname");
        name_lbl.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        name_lbl.setStyle("-fx-text-fill:BLACK");
        GridPane.setConstraints(name_lbl, 0, 2);

        //Text field to input name
        final TextField name_txt = new TextField();
        name_txt.setPromptText("Enter your nickname!!");
        name_txt.setFont(Font.font("Century Gothic"));
        name_txt.setStyle("-fx-text-box-border:BLACK; -fx-focus-color:BLACK;");
        GridPane.setConstraints(name_txt, 0, 3);

        //Button to navigate to next scene
        Button btn = new Button();
        btn.setText("Lets Brain");
        btn.setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
        btn.setStyle("-fx-background-color: BLACK; -fx-text-fill: WHITE;");
        GridPane.setConstraints(btn, 0, 4);
        btn.setDefaultButton(true);

        //Button action handler 
        btn.setOnAction(e -> {
            nick_name = name_txt.getText();
            System.out.println(name_txt);
            try {
                hostPage();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Brainmania.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        GridPane name_grid = new GridPane();
        name_grid.setPadding(new Insets(10, 10, 10, 10));
        name_grid.setAlignment(Pos.CENTER);
        name_grid.setVgap(5);
        name_grid.setHgap(5);
        name_grid.setStyle("-fx-background-color: CADETBLUE");
        name_grid.getChildren().addAll(logo_home_view, name_lbl, name_txt, btn);

        //Home page scene
        home = new Scene(name_grid, 500, 500);

        Window.setTitle("BrainMania");
        Window.setScene(home);
        Window.show();

    }

    public void hostPage() throws FileNotFoundException {
        //host page

        //calling logo
        Image logo_host = new Image(new FileInputStream("images/BrainMania.png"));
        ImageView logo__host_view = new ImageView(logo_host);
        logo__host_view.setFitHeight(50);
        logo__host_view.setFitWidth(200);
//        GridPane.setConstraints(logo__host_view, 0, 0);

        //calling input value from previous scene
        Label nick_name_lbl = new Label("Hi " + nick_name + " !!");
        nick_name_lbl.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        nick_name_lbl.setStyle("-fx-text-fill:BLACK");
//        GridPane.setConstraints(nick_name_lbl, 0, 2);

        //Label foe selecting number of questions
        Label no_of_questions = new Label("Select number of questions");
        no_of_questions.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        no_of_questions.setStyle("-fx-text-fill:BLACK");
//        GridPane.setConstraints(no_of_questions, 0, 3);

        //Button to select number of questions
        Button no_question_btn1 = new Button();
        no_question_btn1.setText("5");
        no_question_btn1.setShape(new Circle(2));
        no_question_btn1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        no_question_btn1.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 50em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; ");

        no_question_btn1.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            amount = "5";
            //since the returned object is a Button you can cast it to one
            Button type_1 = (Button) node;
            System.out.println(type_1.getText());
        });

        Button no_question_btn2 = new Button();
        no_question_btn2.setText("10");
        no_question_btn2.setShape(new Circle(2));
        no_question_btn2.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        no_question_btn2.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 50em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; ");

        no_question_btn2.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            amount = "10";
            Button type_2 = (Button) node;
            System.out.println(type_2.getText());
        });

        Button no_question_btn3 = new Button();
        no_question_btn3.setText("15");
        no_question_btn3.setShape(new Circle(2));
        no_question_btn3.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        no_question_btn3.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 50em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; ");

        no_question_btn3.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            amount = "15";
            //since the returned object is a Button you can cast it to one
            Button type_3 = (Button) node;
            System.out.println(type_3.getText());
        });

        HBox question_btns = new HBox(25); // 5 is the spacing between elements in the VBox
        question_btns.getChildren().addAll(no_question_btn1, no_question_btn2, no_question_btn3);
//        GridPane.setConstraints(question_btns, 0, 4);

        //Label to select the category of questions
        Label category_lbl = new Label("Select a category");
        category_lbl.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        category_lbl.setStyle("-fx-text-fill:BLACK");
//        GridPane.setConstraints(category_lbl, 0, 5);

        //Button to select the category of questions
        Button category_btn1 = new Button();
        category_btn1.setText("Sports");
        category_btn1.setShape(new Circle(2));
        category_btn1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        category_btn1.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 80em; "
                + "-fx-min-width: 80px; "
                + "-fx-min-height: 80px; ");

        category_btn1.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            System.out.println(node instanceof Button); //prints true. demonstrates the source is a Button
            //since the returned object is a Button you can cast it to one
            Button category1 = (Button) node;
            System.out.println(category1.getText());
            try {
                category = "21";

                String ques[] = {"", "", "", ""};
                questionPage("", "", ques);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Brainmania.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button category_btn2 = new Button();
        category_btn2.setText("Movies");
        category_btn2.setShape(new Circle(2));
        category_btn2.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        category_btn2.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 80em; "
                + "-fx-min-width: 80px; "
                + "-fx-min-height: 80px; ");

        category_btn2.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            System.out.println(node instanceof Button); //prints true. demonstrates the source is a Button
            //since the returned object is a Button you can cast it to one
            Button category2 = (Button) node;
            System.out.println(category2.getText());
            try {
                category = "11";
                String ques[] = {"", "", "", ""};
                questionPage("", "", ques);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Brainmania.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button category_btn3 = new Button();
        category_btn3.setText("Animals");
        category_btn3.setShape(new Circle(2));
        category_btn3.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        category_btn3.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 80em; "
                + "-fx-min-width: 80px; "
                + "-fx-min-height: 80px; ");

        category_btn3.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            System.out.println(node instanceof Button); //prints true. demonstrates the source is a Button
            //since the returned object is a Button you can cast it to one
            Button category3 = (Button) node;
            System.out.println(category3.getText());
            try {
                category = "27";
                String ques[] = {"", "", "", ""};
                questionPage("", "", ques);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Brainmania.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        HBox category_btns = new HBox(25); // 5 is the spacing between elements in the VBox
        category_btns.getChildren().addAll(category_btn1, category_btn2, category_btn3);
//        GridPane.setConstraints(category_btns, 0, 6);

        VBox fields = new VBox(25);
        fields.getChildren().addAll(logo__host_view, nick_name_lbl, no_of_questions, question_btns, category_lbl, category_btns);
        GridPane.setConstraints(fields, 0, 0);

        GridPane host_grid = new GridPane();
        host_grid.setPadding(new Insets(10, 10, 10, 10));
        host_grid.setAlignment(Pos.CENTER);
        host_grid.setVgap(5);
        host_grid.setHgap(5);
        host_grid.setStyle("-fx-background-color: CADETBLUE");
//        host_grid.getChildren().addAll(logo__host_view,nick_name_lbl,no_of_questions,
//                question_btns,category_lbl,category_btns);
        host_grid.getChildren().addAll(fields);

        //Host page scene
        host = new Scene(host_grid, 500, 500);

        Window.setScene(host);

    }

    public void questionPage(String question, String correct, String opts[]) throws FileNotFoundException {
        //host page

        //calling logo
        Image logo_host = new Image(new FileInputStream("images/BrainMania.png"));
        ImageView logo__host_view = new ImageView(logo_host);
        logo__host_view.setFitHeight(50);
        logo__host_view.setFitWidth(200);
//        GridPane.setConstraints(logo__host_view, 0, 0);

        //calling input value from previous scene
        Label nick_name_lbl = new Label("Hi " + nick_name + " !!");
        nick_name_lbl.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        nick_name_lbl.setStyle("-fx-text-fill:BLACK");
//        GridPane.setConstraints(nick_name_lbl, 0, 2);

        //Label foe selecting number of questions
        Label no_of_questions = new Label("Question:" + question);
        no_of_questions.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        no_of_questions.setStyle("-fx-text-fill:BLACK");
//        GridPane.setConstraints(no_of_questions, 0, 3);

        //Button to select number of questions
        Collections.shuffle(Arrays.asList(opts));
        Button question_btn1 = new Button();
        question_btn1.setText(opts[0]);
        question_btn1.setShape(new Circle(2));
        question_btn1.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        question_btn1.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 50em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; ");

        question_btn1.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            Button type_1 = (Button) node;
            System.out.println(type_1.getText());
            selected_Answer = type_1.getText();
        });

        Button question_btn2 = new Button();
        question_btn2.setText(opts[1]);
        question_btn2.setShape(new Circle(2));
        question_btn2.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        question_btn2.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 50em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; ");

        question_btn2.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            Button type_2 = (Button) node;
            System.out.println(type_2.getText());
            selected_Answer = type_2.getText();
        });

        Button question_btn3 = new Button();
        question_btn3.setText(opts[2]);
        question_btn3.setShape(new Circle(2));
        question_btn3.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        question_btn3.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 50em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; ");

        question_btn3.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            Button type_3 = (Button) node;
            System.out.println(type_3.getText());
            selected_Answer = type_3.getText();
        });
        Button question_btn4 = new Button();
        question_btn4.setText(opts[3]);
        question_btn4.setShape(new Circle(2));
        question_btn4.setFont(Font.font("Century Gothic", FontWeight.BOLD, 18));
        question_btn4.setStyle("-fx-text-fill:BLACK;"
                + "-fx-background-radius: 50em; "
                + "-fx-min-width: 50px; "
                + "-fx-min-height: 50px; ");

        question_btn4.setOnAction(event -> {
            Object node = event.getSource(); //returns the object that generated the event
            Button type_4 = (Button) node;
            System.out.println(type_4.getText());
            selected_Answer = type_4.getText();
        });

        HBox question_btns = new HBox(25); // 5 is the spacing between elements in the VBox
        question_btns.getChildren().addAll(question_btn1, question_btn2, question_btn3, question_btn4);

        Button btn = new Button();
        btn.setText("Submit");
        btn.setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
        btn.setStyle("-fx-background-color: BLACK; -fx-text-fill: WHITE;");
        GridPane.setConstraints(btn, 0, 4);
        btn.setDefaultButton(true);

        /*Interval*/
 /*End of interval*/
        VBox fields = new VBox(25);
        fields.getChildren().addAll(logo__host_view, nick_name_lbl, no_of_questions, question_btns, btn);
        GridPane.setConstraints(fields, 0, 0);

        GridPane host_grid = new GridPane();
        host_grid.setPadding(new Insets(10, 10, 10, 10));
        host_grid.setAlignment(Pos.CENTER);
        host_grid.setVgap(5);
        host_grid.setHgap(5);
        host_grid.setStyle("-fx-background-color: CADETBLUE");
//        host_grid.getChildren().addAll(logo__host_view,nick_name_lbl,no_of_questions,
//                question_btns,category_lbl,category_btns);
        host_grid.getChildren().addAll(fields);

        try {
            questions_remaining = Integer.valueOf(amount);
            String _url = "https://opentdb.com/api.php?amount=" + amount + "&category=" + category + "&type=" + type;
            //System.out.println("URL : " + _url);
            URL url = new URL(_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JSONObject _jo = new JSONObject(response.toString());
                questions_all = new JSONArray(_jo.getJSONArray("results"));
                Collections.shuffle(questions_all.toList());
                JSONObject _tmp = new JSONObject(questions_all.get(now).toString());
                now++;
                //System.out.println("L431 : " + _tmp.toString());
                no_of_questions.setText(_tmp.getString("question"));
                String op[] = new String[4];

                JSONArray _tmp1 = new JSONArray(_tmp.getJSONArray("incorrect_answers"));
                for (int j = 0; j < 3; j++) {
                    op[j] = _tmp1.getString(j);
                }
                op[3] = _tmp.getString("correct_answer");
                correct_Answer = _tmp.getString("correct_answer");
                Collections.shuffle(Arrays.asList(op));
                question_btn1.setText(op[0]);
                question_btn2.setText(op[1]);
                question_btn3.setText(op[2]);
                question_btn4.setText(op[3]);
                questions_remaining--;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            btn.setOnAction(e -> {
                if (selected_Answer.equals(correct_Answer)) {
                    Right_Answers++;
                }

                if (questions_remaining > 0 && now < questions_all.length()) {
                    System.out.println("L458 : " + questions_all.length());
                    JSONObject _tmp1 = new JSONObject(questions_all.get(now).toString());
                    System.out.println("L460 : " + questions_all.length());
                    now++;
                    System.out.println("L462 : " + questions_all.length());

                    no_of_questions.setText(_tmp1.getString("question"));
                    String op[] = new String[4];

                    JSONArray _tmp12 = new JSONArray(_tmp1.getJSONArray("incorrect_answers"));
                    for (int j = 0; j < 3; j++) {
                        op[j] = _tmp12.getString(j);
                    }
                    op[3] = _tmp1.getString("correct_answer");
                    correct_Answer = _tmp1.getString("correct_answer");
                    Collections.shuffle(Arrays.asList(op));
                    question_btn1.setText(op[0]);
                    question_btn2.setText(op[1]);
                    question_btn3.setText(op[2]);
                    question_btn4.setText(op[3]);
                    questions_remaining--;

                } else {
                    int score = Point_per_question * Right_Answers ;
                      String res = "Ended";
                    if(getPercentile(score,Integer.valueOf(amount)) > 50){
                        res =" Passed ";
                    }
                    else{
                        res = " Failed ";
                    }
                    
                  
                    
                    String result_remarks = "QUIZ "+res+"\nYou Answered " + Right_Answers + " right out of " + amount + " Questions\nYour Score : "+score;
                    no_of_questions.setText(result_remarks);
                    question_btn1.setVisible(false);
                    question_btn2.setVisible(false);
                    question_btn3.setVisible(false);
                    question_btn4.setVisible(false);
                    btn.setVisible(false);
                }

            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        host = new Scene(host_grid, 500, 500);
        Window.setScene(host);
    }
    private int  getPercentile(int x,int y){
        double res = 0;
        res = (x*100)/y;
        return (int)res;
    }
    private String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String startDate = formatter.format(date);
        return startDate;
    }

    private void TimeDifference(String startTime) {

        try {
            String time1 = startTime;
            Date date = new Date();

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String time2 = formatter.format(date);
            Date date1 = formatter.parse(time1);
            Date date2 = formatter.parse(time2);
            time_diff = date2.getTime() - date1.getTime();

        } catch (Exception e) {
            System.out.println("E429 : " + e.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
