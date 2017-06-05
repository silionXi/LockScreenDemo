//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 15 "calc.y"
  package com.sec.NT.expression;
  
  import java.io.*;
  
	import com.sec.NT.logic.NTOperation;
	import com.sec.NT.logic.NTValue;
	import com.sec.NT.logic.NTValueFloat;
	import com.sec.NT.logic.NTValueInteger;
	import com.sec.NT.logic.NTValueString;
//#line 27 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short NL=257;
public final static short INT_OBJ=258;
public final static short FLOAT_OBJ=259;
public final static short EXP_OBJ=260;
public final static short STRING_OBJ=261;
public final static short MAX=262;
public final static short MIN=263;
public final static short EQ=264;
public final static short NE=265;
public final static short GE=266;
public final static short GT=267;
public final static short LE=268;
public final static short LT=269;
public final static short NOT=270;
public final static short ADD=271;
public final static short SUB=272;
public final static short MUL=273;
public final static short DIV=274;
public final static short MOD=275;
public final static short LP=276;
public final static short RP=277;
public final static short COMMA=278;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    7,    7,    6,    6,    6,    5,    5,    5,
    5,    5,    1,    1,    1,    2,    2,    2,    2,    3,
    3,    4,    4,    4,    4,    4,    4,    4,
};
final static short yylen[] = {                            2,
    0,    2,    1,    2,    1,    3,    3,    1,    3,    3,
    3,    3,    1,    3,    3,    1,    3,    3,    3,    1,
    2,    1,    1,    1,    1,    3,    6,    6,
};
final static short yydefred[] = {                         1,
    0,    3,   22,   23,   25,   24,    0,    0,    0,    0,
    0,    0,   16,   20,    0,    0,    2,    0,    0,   21,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    4,    0,    0,    0,    0,   26,    0,    0,   17,   18,
   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   27,   28,
};
final static short yydgoto[] = {                          1,
   11,   12,   13,   14,   15,   16,   17,
};
final static short yysindex[] = {                         0,
 -133,    0,    0,    0,    0,    0, -272, -269, -114, -114,
 -270, -220,    0,    0,  -93,  -94,    0, -114, -114,    0,
 -124, -114, -114, -114, -114, -114, -114, -114, -114, -114,
    0, -114, -114, -259, -166,    0, -220, -220,    0,    0,
    0, -270, -270, -270, -270,  -93,  -93, -114, -114, -110,
 -108,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -205, -254,    0,    0, -155,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -237, -221,    0,    0,
    0, -199, -183, -177, -161, -146, -144,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
  150,   -1,  126,    0,   -7,  -10,    0,
};
final static int YYTABLESIZE=180;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         21,
   22,   23,   13,   18,   32,   33,   19,   34,   35,   13,
   13,   13,   13,   13,   13,    0,   13,   13,   48,   14,
   37,   38,   13,   13,   46,   47,   14,   14,   14,   14,
   14,   14,    0,   14,   14,   15,    0,   50,   51,   14,
   14,    0,   15,   15,   15,   15,   15,   15,    0,   15,
   15,    8,   24,   25,   26,   15,   15,   10,    8,    8,
    8,    8,    8,    8,   10,   10,   10,   10,   10,   10,
    0,    8,    8,    9,    0,    0,    0,   10,   10,   12,
    9,    9,    9,    9,    9,    9,   12,   12,   12,   12,
   12,   12,    0,    9,    9,   11,    0,   32,   33,   12,
   12,    5,   11,   11,   11,   11,   11,   11,    5,    5,
    6,   49,    7,    0,    0,   11,   11,    6,    6,    7,
    7,    5,    5,    2,    3,    4,    5,    6,    7,    8,
    6,    6,    7,    7,   20,    0,    0,    0,    9,   32,
   33,    0,   10,    3,    4,    5,    6,    7,    8,   39,
   40,   41,   36,   32,   33,   32,   33,    9,    0,    0,
    0,   10,   31,    0,    0,    0,   52,    0,   53,   32,
   33,    0,   27,   28,   29,   30,   42,   43,   44,   45,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         10,
  271,  272,  257,  276,  264,  265,  276,   18,   19,  264,
  265,  266,  267,  268,  269,   -1,  271,  272,  278,  257,
   22,   23,  277,  278,   32,   33,  264,  265,  266,  267,
  268,  269,   -1,  271,  272,  257,   -1,   48,   49,  277,
  278,   -1,  264,  265,  266,  267,  268,  269,   -1,  271,
  272,  257,  273,  274,  275,  277,  278,  257,  264,  265,
  266,  267,  268,  269,  264,  265,  266,  267,  268,  269,
   -1,  277,  278,  257,   -1,   -1,   -1,  277,  278,  257,
  264,  265,  266,  267,  268,  269,  264,  265,  266,  267,
  268,  269,   -1,  277,  278,  257,   -1,  264,  265,  277,
  278,  257,  264,  265,  266,  267,  268,  269,  264,  265,
  257,  278,  257,   -1,   -1,  277,  278,  264,  265,  264,
  265,  277,  278,  257,  258,  259,  260,  261,  262,  263,
  277,  278,  277,  278,    9,   -1,   -1,   -1,  272,  264,
  265,   -1,  276,  258,  259,  260,  261,  262,  263,   24,
   25,   26,  277,  264,  265,  264,  265,  272,   -1,   -1,
   -1,  276,  257,   -1,   -1,   -1,  277,   -1,  277,  264,
  265,   -1,  266,  267,  268,  269,   27,   28,   29,   30,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=278;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"NL","INT_OBJ","FLOAT_OBJ","EXP_OBJ","STRING_OBJ","MAX","MIN",
"EQ","NE","GE","GT","LE","LT","NOT","ADD","SUB","MUL","DIV","MOD","LP","RP",
"COMMA",
};
final static String yyrule[] = {
"$accept : input",
"input :",
"input : input line",
"line : NL",
"line : equality_expression NL",
"equality_expression : relational_expression",
"equality_expression : equality_expression EQ relational_expression",
"equality_expression : equality_expression NE relational_expression",
"relational_expression : additive_expression",
"relational_expression : relational_expression GT additive_expression",
"relational_expression : relational_expression GE additive_expression",
"relational_expression : relational_expression LT additive_expression",
"relational_expression : relational_expression LE additive_expression",
"additive_expression : multiplicative_expression",
"additive_expression : additive_expression ADD multiplicative_expression",
"additive_expression : additive_expression SUB multiplicative_expression",
"multiplicative_expression : unary_expression",
"multiplicative_expression : multiplicative_expression MUL unary_expression",
"multiplicative_expression : multiplicative_expression DIV unary_expression",
"multiplicative_expression : multiplicative_expression MOD unary_expression",
"unary_expression : primary_expression",
"unary_expression : SUB unary_expression",
"primary_expression : INT_OBJ",
"primary_expression : FLOAT_OBJ",
"primary_expression : STRING_OBJ",
"primary_expression : EXP_OBJ",
"primary_expression : LP equality_expression RP",
"primary_expression : MAX LP equality_expression COMMA equality_expression RP",
"primary_expression : MIN LP equality_expression COMMA equality_expression RP",
};

//#line 163 "calc.y"

  private Yylex lexer;
  
  private NTValue result_val = null;
	public NTValue getResult() {
		return result_val;
	}

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
    //yydebug= true;
  }

  public static NTValue parseExpression(String expression)
  {
	  Parser yyparser;
	  yyparser = new Parser(new StringReader(expression+ '\n'));
	  yyparser.yyparse();
	  
	  NTValue ret = yyparser.getResult();
	  //VAL_TYPE t = ret.getType();
	  return ret;
  }

  static boolean interactive;
/*
  public static void main(String args[]) throws IOException {
    System.out.println("BYACC/Java with JFlex Calculator Demo");

	
    Parser yyparser;
 
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
      
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Expression: ");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    //yyparser = new Parser(new StringReader("3 +4*5 \n"));
    yyparser.yyparse();
    
    NTValue ret = yyparser.getResult();
    VAL_TYPE t = ret.getType();
    
    if (interactive) {
      System.out.println();
      System.out.println("Have a nice day");
    }
  }
*/

//#line 340 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 3:
//#line 45 "calc.y"
{ result_val = null;
					/*if (interactive) System.out.print("Expression: "); */
					}
break;
case 4:
//#line 48 "calc.y"
{ result_val = (NTValue)val_peek(1).obj;
       				/*System.out.println(" = " + $1); */
                   /*if (interactive) System.out.print("Expression: "); */
                   }
break;
case 6:
//#line 59 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_CMPEQ((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 7:
//#line 63 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_CMPNE((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 9:
//#line 71 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_CMPGT((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 10:
//#line 75 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_CMPGE((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 11:
//#line 79 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_CMPLT((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 12:
//#line 83 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_CMPLE((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 14:
//#line 92 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_ADD((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 15:
//#line 96 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_SUB((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 17:
//#line 106 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_MUL((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 18:
//#line 110 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_DIV((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 19:
//#line 114 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_MOD((NTValue)val_peek(2).obj, (NTValue)val_peek(0).obj);
			}
break;
case 21:
//#line 124 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_MINUS((NTValue)val_peek(0).obj);
			}
break;
case 22:
//#line 132 "calc.y"
{
				yyval.obj = new NTValueInteger(val_peek(0).ival);
			}
break;
case 23:
//#line 136 "calc.y"
{
				yyval.obj = new NTValueFloat(val_peek(0).dval);
			}
break;
case 24:
//#line 140 "calc.y"
{
				yyval.obj = new NTValueString(val_peek(0).sval);
			}
break;
case 25:
//#line 144 "calc.y"
{
				yyval.obj = val_peek(0).obj;
			}
break;
case 26:
//#line 148 "calc.y"
{
				yyval.obj = val_peek(1).obj;
			}
break;
case 27:
//#line 152 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_MAX((NTValue)val_peek(3).obj, (NTValue)val_peek(1).obj);
			}
break;
case 28:
//#line 156 "calc.y"
{
				yyval.obj = NTOperation.CreateOP_MIN((NTValue)val_peek(3).obj, (NTValue)val_peek(1).obj);
			}
break;
//#line 616 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
