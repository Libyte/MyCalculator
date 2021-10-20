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
     * 添加数字，从0~9
     * 当目前输入框中只有-0或+0或0时输入数字只覆盖0
     * 当目前输入框为0时覆盖
     * 其他情况直接在后面置数
     * @param existedText 当前输入文本框中的运算式
     * @param num 加入的数字
     * @return 经判断加入后的数字
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
     * 添加运算符，包括加减乘除
     * 当目前输入框为0时，加减改为正负表示，乘除不影响
     * 当目前输入框以小数点借位，提示错误
     * 当乘除时，可以互换符号
     * 乘除正负数时，正负可互换，运算符不可以
     * 其余正常添加
     * @param existedText 当前输入文本框中的运算式
     * @param symbol 加入的运算符
     * @return 经判断加入后的运算式
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
     * 添加小数点
     * 这里使用一个currentPoint变量进行计数，每添加一个符号后更新，用于标志当前数字0
     * currentPoint不为0时，判断子串
     * 为0则表示经过计算或未加入操作符，直接判断是否有小数点
     * @param existedText 当前有的运算式
     * @return 经判断后添加的计算式
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
     * 用于添加阶乘符号
     * 只计算整数
     * 子串中有小数点则直接不计算
     * 限制溢出，只能计算一次
     * @param existedText 当前输入框的运算式
     * @return 经过输入判别后的运算式
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
     * 进行删除操作
     * @param existedText 当前运算式
     * @return 删除后的运算式
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
     * 用于计算运算式的结果
     * 测试返回值，如果为无穷大或非数字，显示不能计算
     * 如果计算出错，使用try catch捕获异常，显示输入错误
     * 其余计算返回计算结果
     * @param existedText 所要计算的表达式
     * @return 计算结果
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
     * 计算，调用中缀转后缀并计算后缀
     * @return 计算结果
     */
    public static double calculate(){
        Queue<String> queue = getPostfixExpression();
        assert queue != null;
        return calculatePostfixExpression(queue);
    }

    /**
     * 中缀转后缀
     * @return 存放后缀表达式的队列
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
     * 计算后缀表达式
     * @param queue 存有后缀表达式的队列
     * @return 计算结果
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
     * 判断字符是否为运算符
     * @param str 要判断的字符
     * @return boolean变量
     */
    public static boolean isOperator(String str){
        return (("+".equals(str)) || ("-".equals(str))
                || ("*".equals(str)) || ("/".equals(str)));
    }

    /**
     * 阶乘计算
     * @param num 计算的整数
     * @return 计算结果
     */
    private static double getFactorial(double num){
        double fac = 1.0;
        for(int i = 1; i <= num; i++){
            fac *= i;
        }
        return fac;
    }

    /**
     * 获取优先级
     * @param symbol 对应符号
     * @return 符号的优先级
     */
    public static int getPriority(char symbol){
        if(symbol == '+' || symbol == '-'){

            return 1;
        }
        return 2;
    }

}