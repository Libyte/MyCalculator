package cn.edu.bistu.cs.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * @author hp
 */
public class MainActivity extends Activity implements View.OnClickListener{
    public static final String TAG = "MainActivity";
    public static StringBuilder stringBuilder;

    private Button num0;
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;

    private Button buttonAdd;
    private Button buttonSub;
    private Button buttonMul;
    private Button buttonDiv;
    private Button buttonFac;
    private Button buttonEq;

    private Button buttonPoint;
    private Button buttonDel;
    private Button buttonAc;

    private TextView inputText;
    private TextView resultText;

    private String existedText = "";

    private int currentPoint = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initView() {

        num0 = findViewById(R.id.num_zero);
        num1 = findViewById(R.id.num_one);
        num2 = findViewById(R.id.num_two);
        num3 = findViewById(R.id.num_three);
        num4 = findViewById(R.id.num_four);
        num5 = findViewById(R.id.num_five);
        num6 = findViewById(R.id.num_six);
        num7 = findViewById(R.id.num_seven);
        num8 = findViewById(R.id.num_eight);
        num9 = findViewById(R.id.num_nine);

        buttonAdd = findViewById(R.id.add_button);
        buttonSub = findViewById(R.id.sub_button);
        buttonMul = findViewById(R.id.mul_button);
        buttonDiv = findViewById(R.id.div_button);
        buttonFac = findViewById(R.id.factorial_button);
        buttonEq = findViewById(R.id.eq_button);

        buttonPoint = findViewById(R.id.point_button);
        buttonDel = findViewById(R.id.delete_btn);
        buttonAc = findViewById(R.id.ac_btn);

        resultText = findViewById(R.id.result_text);
        inputText = findViewById(R.id.input_text);

        existedText = inputText.getText().toString();




    }


    private void initEvent() {
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);

        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonFac.setOnClickListener(this);
        buttonEq.setOnClickListener(this);

        buttonPoint.setOnClickListener(this);
        buttonDel.setOnClickListener(this);
        buttonAc.setOnClickListener(this);
    }




    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.num_zero:
                existedText = addNum(existedText, "0");
                break;
            case R.id.num_one:
                existedText = addNum(existedText, "1");
                break;
            case R.id.num_two:
                existedText = addNum(existedText, "2");
                break;
            case R.id.num_three:
                existedText = addNum(existedText, "3");
                break;
            case R.id.num_four:
                existedText = addNum(existedText, "4");
                break;
            case R.id.num_five:
                existedText = addNum(existedText, "5");
                break;
            case R.id.num_six:
                existedText = addNum(existedText, "6");
                break;
            case R.id.num_seven:
                existedText = addNum(existedText, "7");
                break;
            case R.id.num_eight:
                existedText = addNum(existedText, "8");
                break;
            case R.id.num_nine:
                existedText = addNum(existedText, "9");
                break;
            case R.id.point_button:
                existedText = addPoint(existedText);
                break;
            case R.id.ac_btn:
                existedText = "0";
                currentPoint = 0;
                break;
            case R.id.delete_btn:
                existedText = getDel(existedText);
                break;
            case R.id.add_button:
                existedText = addSymbol(existedText, "+");
                break;
            case R.id.sub_button:
                existedText = addSymbol(existedText, "-");
                break;
            case R.id.div_button:
                existedText = addSymbol(existedText, "/");
                break;
            case R.id.mul_button:
                existedText = addSymbol(existedText, "*");
                break;
            case R.id.factorial_button:
                existedText = addFac(existedText);
                break;
            case R.id.eq_button:
                existedText = getEq(existedText);
                break;
            default:
            break;
        }
        inputText.setText(existedText);
    }

    /**
     * ??????????????????0~9
     * ???????????????????????????-0???+0???0????????????????????????0
     * ?????????????????????0?????????
     * ?????????????????????????????????
     * @param existedText ????????????????????????????????????
     * @param num ???????????????
     * @return ???????????????????????????
     */
    public String addNum(String existedText, String num){
        if(existedText.length() == 2
                && existedText.charAt(existedText.length() - 1) == '0'){

            existedText = existedText.substring(0, existedText.length() - 1).concat(num);
        }
        else if("0".equals(existedText) && "0".equals(num)){

            existedText = "0";
        }
        else if("0".equals(existedText)){
            existedText = num;
        }

        else {
            existedText = existedText.concat(num);
            resultText.setText("0");
        }

        return existedText;
    }

    /**
     * ????????????????????????????????????
     * ?????????????????????0????????????????????????????????????????????????
     * ???????????????????????????????????????????????????
     * ?????????????????????????????????
     * ?????????????????????????????????????????????????????????
     * ??????????????????
     * @param existedText ????????????????????????????????????
     * @param symbol ??????????????????
     * @return ??????????????????????????????
     */
    @SuppressLint("SetTextI18n")
    public String addSymbol(String existedText, String symbol){
        if(existedText.charAt(existedText.length()-1) == '.'){

            Toast.makeText(MainActivity.this,
                    "input error", Toast.LENGTH_SHORT).show();
        }
        else if("0".equals(existedText)){
            if("-".equals(symbol) || "+".equals(symbol)){

                currentPoint = 0;
                existedText = symbol + "0";
            }
            else{
                existedText = existedText.concat(symbol);
                currentPoint = existedText.length();
            }
        }
        else if(isOperator(existedText.charAt(existedText.length() - 1) + "")){
            if(!isOperator(existedText.charAt(existedText.length() - 2) + "")){

                if (!"-".equals(symbol) && !"+".equals(symbol)) {

                    existedText = existedText.substring(0, existedText.length() - 1).concat(symbol);
                    currentPoint = existedText.length();
                }
                else{
                    existedText = existedText.concat(symbol);
                    currentPoint = existedText.length() - 1;
                }



            }
            else{
                if("-".equals(symbol) || "+".equals(symbol)) {

                    existedText = existedText.substring(0, existedText.length() - 1);
                    existedText = existedText.concat(symbol);
                    currentPoint = existedText.length();
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "input error", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{

            existedText = existedText.concat(symbol);
            currentPoint = existedText.length();
            resultText.setText("0");
        }
        return existedText;
    }

    /**
     * ???????????????
     * ??????????????????currentPoint??????????????????????????????????????????????????????????????????????????????0
     * currentPoint??????0??????????????????
     * ???0???????????????????????????????????????????????????????????????????????????
     * @param existedText ?????????????????????
     * @return ??????????????????????????????
     */
    @SuppressLint("SetTextI18n")
    public String addPoint(String existedText){
        //Log.e(TAG, String.valueOf(currentPoint));
        if(currentPoint == 0 && existedText.contains(".")){
            Toast.makeText(MainActivity.this,
                    "input error",Toast.LENGTH_SHORT).show();
        }
        else if(isOperator(existedText.charAt(existedText.length() - 1) + "")
                    || existedText.charAt(existedText.length()-1) == '.'
                    || existedText.charAt(existedText.length() - 1) == '!'
                ){
            Toast.makeText(MainActivity.this,
                    "input error",Toast.LENGTH_SHORT).show();
        }
        else if(existedText.charAt(existedText.length()-1) == '0'){
            existedText = existedText.concat(".");
        }
        else if(existedText.substring(currentPoint, existedText.length()-1).contains(".") ){
            if(!existedText.substring(currentPoint, existedText.length()-1).contains("+")
                    || !existedText.substring(currentPoint, existedText.length()-1).contains("-")
                    || !existedText.substring(currentPoint, existedText.length()-1).contains("*")
                    || !existedText.substring(currentPoint, existedText.length()-1).contains("/")) {
                Toast.makeText(MainActivity.this,
                        "input error", Toast.LENGTH_SHORT).show();
            }
        }

        else{
            existedText = existedText.concat(".");
        }
        return existedText;
    }

    /**
     * ????????????????????????
     * ???????????????
     * ???????????????????????????????????????
     * ?????????????????????????????????
     * @param existedText ???????????????????????????
     * @return ?????????????????????????????????
     */
    public String addFac(String existedText){
        if(existedText.charAt(existedText.length() - 1) == '.'){
            Toast.makeText(MainActivity.this,
                    "input illegal", Toast.LENGTH_SHORT).show();
            return existedText;
        }
        else if(existedText.charAt(existedText.length() - 1) == '!'){
            Toast.makeText(MainActivity.this,
                    "cannot calculate double '!'", Toast.LENGTH_SHORT).show();
            return existedText;
        }
        else if(isOperator(existedText.charAt(existedText.length() - 1) + "")){
            Toast.makeText(MainActivity.this,
                    "input illegal", Toast.LENGTH_SHORT).show();
            return existedText;
        }
        else if(currentPoint == 0){
                if(existedText.charAt(0) == '-'){
                     Toast.makeText(MainActivity.this,
                             "input illegal", Toast.LENGTH_SHORT).show();
                }
                else if(existedText.contains(".")){
                    Toast.makeText(MainActivity.this,
                            "cannot calculate float in factorial",Toast.LENGTH_SHORT).show();
                }
                else{
                     existedText = existedText.concat("!");
                }
        }
        else{
            if(existedText.substring(currentPoint).contains(".")){
                Toast.makeText(MainActivity.this,
                        "cannot calculate float in factorial",Toast.LENGTH_SHORT).show();
            }
            else if(existedText.charAt(currentPoint) == '-'){
                Log.e(TAG, currentPoint +"");
                Toast.makeText(MainActivity.this,
                        "input illegal", Toast.LENGTH_SHORT).show();
            }
            else{
                existedText = existedText.concat("!");
            }
        }

        return existedText;
    }

    /**
     * ??????????????????
     * @param existedText ???????????????
     * @return ?????????????????????
     */
    public String getDel(String existedText){
        if("0".equals(existedText)){
            return existedText;
        }
        else if(existedText.length() == 1){
            existedText = "0";
        }
        else if(isOperator(existedText.charAt(existedText.length() - 1) + "")){
            Toast.makeText(MainActivity.this,
                    "please use ac clear operator", Toast.LENGTH_SHORT).show();
        }
        else {
            existedText = existedText.substring(0, existedText.length()-1);
        }
        return existedText;
    }

    public String getEq(String existedText){
        if( existedText.contains("E")
                || isOperator(existedText.charAt(existedText.length() - 1) + "")){

            Toast.makeText(MainActivity.this,
                    "input illegal", Toast.LENGTH_SHORT).show();
            return existedText;
        }
        resultText.setText(existedText);
        existedText = getResult(existedText);
        if("-0.0".equals(existedText)){
            existedText = "0.0";
        }
        currentPoint = 0;
        return existedText;
    }


    /**
     * ??????????????????????????????
     * ?????????????????????????????????????????????????????????????????????
     * ???????????????????????????try catch?????????????????????????????????
     * ??????????????????????????????
     * @param existedText ????????????????????????
     * @return ????????????
     */
    public String getResult(String existedText){
        stringBuilder = new StringBuilder(existedText);
        double result;
        String resStr;
        try{

            result = calculate();

            if(Double.isNaN(result) || Double.isInfinite(result)){

                Log.e(TAG, "test1");
                inputText.setText("0");
                Toast.makeText(MainActivity.this,
                        "cannot calculate",Toast.LENGTH_SHORT).show();
                return "0";

            }

            resStr = String.valueOf(result);

        }catch (Exception e){

            inputText.setText("0");
            e.printStackTrace();
            Toast.makeText(MainActivity.this,
                    "input error",Toast.LENGTH_SHORT).show();
            Log.e(TAG, "test2");

            return "0";
        }
        return resStr;
    }

    /**
     * ?????????????????????????????????????????????
     * @return ????????????
     */
    public static double calculate(){
        Queue<String> queue = getPostfixExpression();
        assert queue != null;
        return calculatePostfixExpression(queue);
    }

    /**
     * ???????????????
     * @return ??????????????????????????????
     */
    public static Queue<String> getPostfixExpression(){
        Stack<Character> symbolStack = new Stack<>();
        StringBuilder str = new StringBuilder();
        Queue<String> postfixQueue = new LinkedList<>();
        int len = stringBuilder.length();
        char c;
        for(int i = 0; i < len; i++){
            c = stringBuilder.charAt(i);

            if(c >= '0' && c <= '9' || c == '.'){
                str.append(c);
            }
            else{
                if(i == 0 || isOperator(stringBuilder.charAt(i - 1) + "")){
                    str.append(c);
                    continue;
                }
                else if(c == '!'){
                    double facNum = Integer.parseInt(str.toString());
                    str.setLength(0);
                    if(facNum < 0){
                        return null;
                    }
                    facNum = getFactorial(facNum);

                    while(i + 1 < len){
                        if(stringBuilder.charAt(i + 1) == '!'){
                            i ++;
                            facNum = getFactorial(facNum);
                        }
                        else{
                            break;
                        }
                    }
                    str.append(facNum);
                }

                if(str.length() != 0){
                    postfixQueue.add(str.toString());
                    str.setLength(0);
                }

                if(!symbolStack.isEmpty()){
                    while(!symbolStack.isEmpty()){
                        char top = symbolStack.peek();
                        if(getPriority(c) <= getPriority(top)){
                            postfixQueue.add(top + "");
                            symbolStack.pop();
                        }
                        else{
                            break;
                        }
                    }
                }
                if(c != '!'){
                    symbolStack.push(c);
                }
            }
        }
        if(str.length() != 0){
            postfixQueue.add(str.toString());
        }
        while(!symbolStack.isEmpty()){
            char top = symbolStack.pop();
            postfixQueue.add(top + "");
        }
        return postfixQueue;
    }

    /**
     * ?????????????????????
     * @param queue ??????????????????????????????
     * @return ????????????
     */
    public static double calculatePostfixExpression(Queue<String> queue){
        Stack<Double> numStack = new Stack<>();
        int len = queue.size();
        double num1;
        double num2;
        double num3 = 0;

        for(int i = 0; i < len; i++){
            String str = queue.poll();
            if(!isOperator(str)){
                assert str != null;
                numStack.push(Double.valueOf(str));
            }
            else{
                num1 = numStack.pop();
                num2 = numStack.pop();

                switch(Objects.requireNonNull(str)){
                    case "+":
                        num3 = num2 + num1;
                        break;
                    case "-":
                        num3 = num2 - num1;
                        break;
                    case "*":
                        num3 = num2 * num1;
                        break;
                    case "/":
                        num3 = num2 / num1;
                    default:
                        break;
                }
                numStack.push(num3);
            }
        }
        return numStack.pop();
    }

    /**
     * ??????????????????????????????
     * @param str ??????????????????
     * @return boolean??????
     */
    public static boolean isOperator(String str){
        return (("+".equals(str)) || ("-".equals(str))
                || ("*".equals(str)) || ("/".equals(str)));
    }

    /**
     * ????????????
     * @param num ???????????????
     * @return ????????????
     */
    private static double getFactorial(double num){
        double fac = 1.0;
        for(int i = 1; i <= num; i++){
            fac *= i;
        }
        return fac;
    }

    /**
     * ???????????????
     * @param symbol ????????????
     * @return ??????????????????
     */
    public static int getPriority(char symbol){
        if(symbol == '+' || symbol == '-'){

            return 1;
        }
        return 2;
    }

}