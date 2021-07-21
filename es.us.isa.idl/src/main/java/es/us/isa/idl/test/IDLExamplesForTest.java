package es.us.isa.idl.test;

public class IDLExamplesForTest {
	public static final String NO_DEPENDENCIES = "";
	
	public static final String ONE_DEPENDENCY_REQUIRES = "IF p1 THEN p2";
	
	public static final String ONE_DEPENDENCY_OR = "Or(p1, p2)";
	
	public static final String ONE_DEPENDENCY_ONLY_ONE = "OnlyOne(p1, p2)";
	
	public static final String ONE_DEPENDENCY_ALL_OR_NONE = "AllOrNone(p1, p2)";
	
	public static final String ONE_DEPENDENCY_ZERO_OR_ONE = "ZeroOrOne(p1, p2)";
	
	public static final String ONE_DEPENDENCY_ARITHM_REL = "p3 <= p5";
	
	public static final String ONE_DEPENDENCY_COMPLEX = "IF p1 THEN OnlyOne(p2, p3)";
	
	public static final String COMBINATORIAL_1 = "Or(p1=='value1', p2=='value1');\n" + 
												"OnlyOne(p2=='value2', p3=='value2');\n" + 
												"AllOrNone(p2=='value3', p3=='value4');\n" + 
												"ZeroOrOne(p4=='value5', p5=='value6');\n" + 
												"p1==p5;";
	
	public static final String COMBINATORIAL_2 = "IF p1 THEN NOT p2;\n" + 
												"IF p2 THEN p3==5;\n" + 
												"IF NOT p1 THEN p4;\n" + 
												"IF p4 THEN p5==p7; // Complex with 2 types\n" + 
												"IF NOT (p4 OR p6) THEN p5 AND p7;\n" + 
												"OnlyOne(Or(p7, p8), Or(p9, p10));\n" + 
												"IF p7 OR p8 THEN p9 OR p10;\n" + 
												"IF p1==true THEN NOT (p2==3 OR p4==4) AND p7;\n" + 
												"IF p3 THEN AllOrNone(p1, p2);\n" + 
												"IF p8 THEN p9;";
	
	public static final String COMBINATORIAL_3 = "Or(p1, p2, p3, p4, p5);\n" + 
												"OnlyOne(p1, p2);\n" + 
												"p4>=p5;\n" + 
												"Or(p1==true, p4==1 OR p2);\n" + 
												"OnlyOne(p3, p5);\n" + 
												"p2!=p3;\n" + 
												"p2==p3;\n" + 
												"OnlyOne(p1 AND p3, p4 AND p5);\n" + 
												"NOT Or(p2=='example', p5==4);\n" + 
												"Or(p3=='value1', p4==1);";
	
	public static final String COMBINATORIAL_4 = "ZeroOrOne(p7 AND p8, p7 AND p8 OR p9=='value5');\n" + 
												"AllOrNone(p4 AND p5, p6 AND p7);\n" + 
												"NOT ZeroOrOne(p5==1000, p4==10000 OR p3==100000, p2==1000000);\n" + 
												"IF p1 AND Or(p2, p3) THEN p1+p2+p4<=999 AND OnlyOne(p6, AllOrNone(p7, p8));\n" + 
												"IF NOT (p4==p5 AND ZeroOrOne(p6, p7)) THEN Or(p6=='value1', p7 AND p8=='value3') OR OnlyOne(p1, p3);";
	
	public static final String COMBINATORIAL_5 = "IF NOT p1==false THEN p2=='something' AND p3==5;\n" + 
												"Or(p4=='string 1'|'string 2', p5<-200, p6==true);\n" + 
												"ZeroOrOne(p7=='another example', p8>=10);\n" + 
												"p3*p5*p8<5;\n" + 
												"Or(p1==p6, p4==p7);";
	
	public static final String COMBINATORIAL_6 = "IF p1=='value1' THEN p2=='value2';\n" + 
												"IF p3<1 THEN p4>5;\n" + 
												"IF NOT (p2<=3 OR p3<=4 OR p2=='value5') AND NOT p1=='value3' THEN p3<=2;\n" + 
												"IF p5<=4 THEN NOT p3<4;\n" + 
												"AllOrNone(p3==3, p3==3);\n" + 
												"AllOrNone(p3 OR p4 OR p4, p1, p2);\n" + 
												"AllOrNone(p1=='invalid value', p2=='another invalid value', p5==10);\n" + 
												"OnlyOne(p5==1, p5==1);\n" + 
												"OnlyOne(p1, p2, p3, p4, p5);";
	
	public static final String COMBINATORIAL_7 = "p1==p2;\n" + 
												"p5>p4;\n" + 
												"p4>p3;\n" + 
												"p3>p5;\n" + 
												"p3+p4+p5<=15;";
	
	public static final String COMBINATORIAL_8 = "IF p2 THEN p4; // Useless/redundant dependency, but valid\n" + 
												"IF p1 THEN (p3==true OR (NOT p3)) AND NOT p7 AND p9=='fixed string';\n" + 
												"Or(p1, p3, p5 AND p7, p9 AND p1);\n" + 
												"NOT Or(p1==true, p2==true, p3==false);\n" + 
												"NOT AllOrNone(p7 AND p9, p8=='something', p5 AND p10=='example');\n" + 
												"AllOrNone(p1, p5);\n" + 
												"NOT ZeroOrOne(p1, p5, p7, p2==false, p6=='example');\n" + 
												"ZeroOrOne(p1, p3);\n" + 
												"AllOrNone(p6!=p8, p8==p10);\n" + 
												"ZeroOrOne(OnlyOne(p5==false, p4==true), OnlyOne(p4, p4==true), OnlyOne(p4==true, p6));";
	
	public static final String COMBINATORIAL_9 = "IF p1 THEN p3==10 OR p5;\n" + 
												"p3 > p1;\n" + 
												"p2 <= p1;\n" + 
												"OnlyOne(p5, p7 OR p9);\n" + 
												"IF p2 THEN p7;";
	
	public static final String COMBINATORIAL_10 = "Or(p1==true, p2=='value1');\n" + 
												"Or(p2=='value2', p4>=4);\n" + 
												"OnlyOne(p2=='value3', p3=='value4');\n" + 
												"OnlyOne(p4<=3, p5>=3);\n" + 
												"ZeroOrOne(p1==false AND p2=='value5', p4==1, p5==2);\n" + 
												"NOT ZeroOrOne(p1==true, p4>1, p5>2);\n" + 
												"p4+p5<=5;\n" + 
												"p4*p5>=4;\n" + 
												"IF Or(p1==false, p4>3) OR NOT ZeroOrOne(p2=='value2', p3=='value5') THEN p2!=p3 AND OnlyOne(p1, p2);\n" + 
												"IF p4+p5<=5 AND p4*p5>=4 THEN p2=='this is the only invalid dependency';";
	
	public static final String CONFLICTIVE_PARAMETER_NAMES_1 = "IF constraint THEN [Accept-Language]; // Useless/redundant dependency, but valid\n" + 
												"IF type THEN (with_underscore==true OR (NOT with_underscore)) AND NOT [something[two]] AND p9=='fixed string';\n" + 
												"Or(type, with_underscore, [index:set] AND [something[two]], p9 AND type);\n" + 
												"NOT Or(type==true, constraint==true, with_underscore==false);\n" + 
												"NOT AllOrNone([something[two]] AND p9, b.b=='something', [index:set] AND p10=='example');\n" + 
												"AllOrNone(type, [index:set]);\n" + 
												"NOT ZeroOrOne(type, [index:set], [something[two]], constraint==false, [something[one]]=='example');\n" + 
												"ZeroOrOne(type, with_underscore);\n" + 
												"AllOrNone([something[one]]!=b.b, b.b==p10);\n" + 
												"ZeroOrOne(OnlyOne([index:set]==false, [Accept-Language]==true), OnlyOne([Accept-Language], [Accept-Language]==true), OnlyOne([Accept-Language]==true, [something[one]]));";
	
	public static final String CONFLICTIVE_PARAMETER_NAMES_2 = "IF type THEN (with_underscore==true OR (NOT with_underscore)) AND NOT [something[two]];\n" + 
												"AllOrNone([something[one]]!=b.b, b.b==p10);";
}
