	var NoOffFirstLineMenus=15;			// Number of first level items
	var LowBgColor='#ffffff';			// Background color when mouse is not over
	var LowSubBgColor='#d6d6d6';			// Background color when mouse is not over on subs
	var HighBgColor='#800000';			// Background color when mouse is over
	var HighSubBgColor='#800000';			// Background color when mouse is over on subs
	var FontLowColor='#800000';			// Font color when mouse is not over
	var FontSubLowColor='#800000';			// Font color subs when mouse is not over
	var FontHighColor='white';			// Font color when mouse is over
	var FontSubHighColor='#ffffff';			// Font color subs when mouse is over
	var BorderColor='white';			// Border color
	var BorderSubColor='#ffffff';			// Border color for subs
	var BorderWidth=1;				// Border width
	var BorderBtwnElmnts=1;			// Border between elements 1 or 0
	var FontFamily="arial,comic sans ms,technical"	// Font family menu items
	var FontSize=9;				// Font size menu items
	var FontBold=0;				// Bold menu items 1 or 0
	var FontItalic=0;				// Italic menu items 1 or 0
	var MenuTextCentered='left';			// Item text position 'left', 'center' or 'right'
	var MenuCentered='left';			// Menu horizontal position 'left', 'center' or 'right'
	var MenuVerticalCentered='top';		// Menu vertical position 'top', 'middle','bottom' or static
	var ChildOverlap=.1;				// horizontal overlap child/ parent
	var ChildVerticalOverlap=.2;			// vertical overlap child/ parent
	var StartTop=130;				// Menu offset x coordinate
	var StartLeft=0;				// Menu offset y coordinate
	var VerCorrect=0;				// Multiple frames y correction
	var HorCorrect=0;				// Multiple frames x correction
	var LeftPaddng=12;				// Left padding
	var TopPaddng=2;				// Top padding
	var FirstLineHorizontal=0;			// SET TO 1 FOR HORIZONTAL MENU, 0 FOR VERTICAL
	var MenuFramesVertical=1;			// Frames in cols or rows 1 or 0
	var DissapearDelay=300;			// delay before menu folds in
	var TakeOverBgColor=1;			// Menu frame takes over background color subitem frame
	var FirstLineFrame='navig';			// Frame where first level appears
	var SecLineFrame='space';			// Frame where sub levels appear
	var DocTargetFrame='space';			// Frame where target documents appear
	var TargetLoc='';				// span id for relative positioning
	var HideTop=0;				// Hide first level when loading new document 1 or 0
	var MenuWrap=1;				// enables/ disables menu wrap 1 or 0
	var RightToLeft=0;				// enables/ disables right to left unfold 1 or 0
	var UnfoldsOnClick=0;			// Level 1 unfolds onclick/ onmouseover
	var WebMasterCheck=0;			// menu tree checking on or off 1 or 0
	var ShowArrow=1;				// Uses arrow gifs when 1
	var KeepHilite=1;				// Keep selected path highligthed
	var Arrws=['/images/arrow-black.gif',4,7,'/images/menu/tridown.gif',10,5,'/images/menu/plusbut.gif',15,15];	// Arrow source, width and height

function BeforeStart(){return}
function AfterBuild(){return}
function BeforeFirstOpen(){return}
function AfterCloseAll(){return}


// Menu tree
//	MenuX=new Array(Text to show, Link, background image (optional), number of sub elements, height, width);
//	For rollover images set "Text to show" to:  "rollover:Image1.jpg:Image2.jpg"

Menu1=new Array("Welcome","/chinese-computing","",0,20,130);

Menu2=new Array("Introduction","/chinese-computing/introduction.html","",0,20,130);

Menu3=new Array("Statistics","/chinese-computing/statistics/","",5);
	Menu3_1=new Array("Character frequency","/chinese-computing/statistics/","",0,20,130);	
	Menu3_2=new Array("Bigram frequency","/chinese-computing/statistics/bigram/form.php","",0);
	Menu3_3=new Array("Phoneme frequency","/chinese-computing/phonology/","",0);
	Menu3_4=new Array("News corpus statistics","/chinese-computing/newscorpus/","",0);
	Menu3_5=new Array("Vocabulary profiler","/chinese-computing/vp/","",0);

		
Menu4=new Array("Search","","",2);
	Menu4_1=new Array("Individual character","/chinese-computing/statistics/char/search.php","",0,20,130);	
	Menu4_2=new Array("Individual bigram","/chinese-computing/statistics/bigram/search.php","",0);
	 
Menu5=new Array("Concordance","/chinese-computing/concord/","",1);
	Menu5_1=new Array("A-not-A questions","/chinese-computing/concord/bu/","",0,20,130);	

Menu6=new Array("Documentation","/chinese-computing/docs/","",2);
	Menu6_1=new Array("Technical report","/chinese-computing/docs/report/","",0,20,130);
	Menu6_2=new Array("How to cite","/chinese-computing/docs/versioning.php","",0,20,130);


Menu7=new Array("FAQ","/chinese-computing/faq/","",8,20,130);
	Menu7_1=new Array("List of topics","/chinese-computing/faq/","",0,20,130);
	Menu7_2=new Array("Display Chinese","/chinese-computing/faq/","",3,20,130);
		Menu7_2_1=new Array("On Windows","/chinese-computing/faq/pc.html","",0,20,130);
		Menu7_2_2=new Array("On Mac","/chinese-computing/faq/mac.html","",0);
		Menu7_2_3=new Array("On Unix","/chinese-computing/faq/","",0);
	Menu7_3=new Array("Chinese encoding","/chinese-computing/faq/","",0,20,130);
	Menu7_4=new Array("Segmenter scripts","/chinese-computing/faq/","",2,20,130);
		Menu7_4_1=new Array("Perl segmenter","/chinese-computing/faq/segmenter.html","",0,20,130);
		Menu7_4_2=new Array("C script","/chinese-computing/faq/cseg.html","",0);
	Menu7_5=new Array("IPA font","/chinese-computing/faq/ipa.php","",0,20,130);
	Menu7_6=new Array("Programming tips","/chinese-computing/faq/programmingtips/","",0,20,130);
	Menu7_7=new Array("Mutual information","/chinese-computing/statistics/mi.html","",0,20,130);
	Menu7_8=new Array("tscore","/chinese-computing/docs/tscore.html","",0,20,130);

Menu8=new Array("Teaching tools","/chinese-computing/teaching/","",3,20,130);
	Menu8_1=new Array("Random character generator","/chinese-computing/statistics/char/random.php","",0,20,130);
	Menu8_2=new Array("Concordancer","/chinese-computing/concord/concordancer.php","",0);
	Menu8_3=new Array("Vocabulary profiler","/chinese-computing/vp/","",0);

Menu9=new Array("Online resources","/chinese-computing/docs/links.html","",0);
Menu10=new Array("Feedback","/contactme.php","",0);
Menu11=new Array("Copyright notice","/chinese-computing/copyright.html","",0);
Menu12=new Array("ChangeLog","/chinese-computing/news.html","",0);
Menu13=new Array("Credits","/chinese-computing/credits.html","",0);
Menu14=new Array("Old version","/chinese-computing/old-version1","",0);
Menu15=new Array("Chinese version","","",0);


