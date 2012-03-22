<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Music India</title>
   <link rel='Stylesheet' href="../../jqwidgets/styles/jqx.base.css" type="text/css" />
    <link rel='Stylesheet' href="../../jqwidgets/styles/jqx.darkblue.css" type="text/css" />
    <link rel='Stylesheet' href="../../styles/mystyle.css" type="text/css" />
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="database.*"%>
    <%@ page import="javax.sql.*"%>
    <%@ page import="javax.sql.*"%>
    
    
    <script type="text/javascript" src="../../scripts/jquery-1.7.1.min.js" ></script>
    <script type="text/javascript" src="../../scripts/gettheme.js" ></script>
    <script type="text/javascript" src="../../jqwidgets/jqxcore.js" ></script>
    <script type="text/javascript" src="../../jqwidgets/jqxdata.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxmenu.js" ></script>
    <script type="text/javascript" src="../../jqwidgets/jqxbuttons.js" ></script>
    <script type="text/javascript" src="../../jqwidgets/jqxexpander.js" ></script>
    <script type="text/javascript" src="../../jqwidgets/jqxnavigationbar.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxdropdownlist.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxgrid.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxgrid.pager.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxgrid.selection.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxwindow.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxpanel.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxtabs.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="js/swfobject.js"></script>
    
    <script type="text/javascript">
    
    	var theme='classic';
    	
    	$(document).ready(function(){
    		
    		$('#searchbutton').jqxButton({width:100,theme:theme});
    		$('#chck').jqxButton({width:100,theme:theme});
    		$('#chck').bind('click',function(){
    			$.post('../../getQuestion',{emailid:foremail},function(data){ alert(data); });
    		});
    		$('#changepass').jqxButton({width:100,theme:theme});
    		$('#changepass').bind('click',function(){
				var prevpass=document.getElementById("previouspass").value;
				var newpass1=document.getElementById("newpass").value;
				var newpass2=document.getElementById("newpass2").value;
				if(newpass1==newpass2)
				{
					$.post('../../changepassword',{prevpass:prevpass,newpass: newpass1},function(data){alert(data)});
				}		
    			
    		});
    		$('#regbut').jqxButton({width:100,theme:theme});
    		$("#regbut").bind('click', function () {
                   var emailid=document.getElementById('emailid').value;
                   var pass=document.getElementById('pass').value;
                   var conpassword=document.getElementById('password1').value;
                   var question=document.getElementById('question').value;
                   var answer=document.getElementById('answer').value;
                   alert("password="+pass+ "conpasswrod="+conpassword);
                   if(password==conpassword)
                   {
                   $.post('../../register',{emaild:emailid,password: password,question:question,answer:answer},function(){alert("Successfully Registered")});
                   }
                   else
                   {
                   	alert('password mismatched');
                   }
                   
                });
    		
    		$('#sign').jqxButton({width:100,theme:theme});
    		$("#searchbutton").bind('click', function () {
                    var s=document.getElementById('search').value;
                    //alert(s);
                    addContest(s);
                });
                $('#regcontainer').jqxWindow({ maxHeight: 400, maxWidth: 700, minHeight: 200, minWidth: 200, height: 300, width: 500, theme: theme });
                $('#regcontainer').jqxWindow('hide');
                $('#forgotpassword').jqxWindow({ maxHeight: 400, maxWidth: 700, minHeight: 200, minWidth: 200, height: 300, width: 500, theme: theme });
                $('#forgotpassword').jqxWindow('hide');
            $('#registration').jqxButton({width:100,theme:theme});
    		$("#registration").bind('click', function () {
                   $('#regcontainer').jqxWindow('show');
                });
            $('#uploadbutton').jqxButton({width:100,theme:theme});
    		$("#uploadbutton").bind('click', function () {
                    $('#uploadcontainer').jqxWindow('show');
                });
                $('#uploadcontainer').jqxWindow({ maxHeight: 400, maxWidth: 700, minHeight: 200, minWidth: 200, height: 300, width: 500, theme: theme });
                $('#uploadcontainer').jqxWindow('hide');
                $('#changepassword').jqxWindow({ maxHeight: 400, maxWidth: 700, minHeight: 200, minWidth: 200, height: 300, width: 500, theme: theme });
                $('#changepassword').jqxWindow('hide');
    		$("#jqxMenu").jqxMenu({ width: '350', mode: 'vertical', theme: theme });
            $("#jqxMenu").css('visibility', 'visible');
            $('#jqxMenu').bind('itemclick', function (event) {
                var args = event.args;
                var s=$(args).text();
                addContest(s);
                
            });
            $('#uploadsubmit').jqxButton({width:100,theme:theme});
            
			            
    	
    	});
    	function addContest(s) {
	//alert(s);
    
    var url = "../../menulist.xml?keyword="+s;
    var source =
            {
                datatype: "xml",
                datafields: [
                    { name: 'Album' },
                    { name: 'Singer' },
                    { name: 'Year'},
                    { name: 'songid'},
                    
               ],
                root: "channel",
                record: "item",
                url: url
            };

            	var linkrenderer = function (row, column, value) {
	                if (value.indexOf('#') != -1) {
	                    value = value.substring(0, value.indexOf('#'));
	                }
                var format = { target: '"_blank"' };
                //var html = $.jqx.dataFormat.formatlink(value, format);
                var html = "<input type='button' value='play' onclick='play("+value+")'/>";
                return html;
            	}
            	var linkrenderer1 = function (row, column, value) {
	                if (value.indexOf('#') != -1) {
	                    value = value.substring(0, value.indexOf('#'));
	                }
                var format = { target: '"_blank"' };
                //var html = $.jqx.dataFormat.formatlink(value, format);
                value=Math.floor(value/10);
                var html = "<input type='button' value='Download' onclick='download("+value+")'/>";
                return html;
            	}

    			// Create jqxGrid.
			    $("#gridcontainer").jqxGrid(
			            {
			                width: 790,
			                height:405,
			                source: source,
			                theme: theme,
			                sortable: true,
			                pageable: true,
			                autoheight: true,
			                columns: [
			                  { text: 'Album', datafield: 'Album', width: 275},
			                  { text: 'Singer', datafield: 'Singer', width: 275 },
			                  { text: 'Year', datafield: 'Year', width: 90, },
			                  { text: 'Play', datafield: 'songid', width: 75,cellsrenderer: linkrenderer },
			                  { text: 'Download', datafield: 'songid', width: 75,cellsrenderer:linkrenderer1 }
			               ]
			            });
			            
			       



		}
		function play(id)
		{
			//alert(id);
			if(id%2==0)
			{
				id=Math.floor(id/10);
				alert("You are going to play mp3 song");
				set(id);
			}
			else
			{
				id=Math.floor(id/10);
				
				
			   alert("you are going to play a video");
			   video(id);
			   }
			
			
		}
		function download(id)
		{
			window.location.href="../../playMusic.mp3?id="+id;
			
		}
		function set(id) {
			//document.getElementById("songname").innerHTML=id;
			//alert("id="+id);
			var a="<object id='flashPlayer' type='application/x-shockwave-flash' data='player_mp3_maxi.swf' width='350' height='25'><param name='wmode' value='transparent' /> <param name='movie' value='player_mp3_maxi.swf' /><param name='FlashVars' value='mp3=../../playMusic.mp3?id="+id+"&amp;autoplay=1&amp;showvolume=1&amp;sliderwidth=25&amp;volumewidth=40&amp;volumeheight=12&amp;bgcolor=E8E8E8&amp;bgcolor1=E8E8E8&amp;bgcolor2=E8E8E8&amp;buttoncolor=828282' /></object>" ;
			document.getElementById("playercontianer").innerHTML=a;

		}
		function video(id) {
		alert("id="+id);
		document.getElementById("playercontianer").innerHTML="<div id='play'></div>";
		
			var flashvars = {
	  vidWidth: "350",
	  vidHeight: "170",
	  vidPath: "http://localhost:8080/Music/playMusic.mp3?id="+id,
	  thumbPath: "http://www.flvplayerlite.com/lab/jpg/playerlite.jpg",
	  autoPlay: "true",
	  autoLoop: "true",
	  watermark: "show",
	  seekbar: "show"
	};
	var params = {
	  menu: "true",
	  allowfullscreen: "true",
	  allowscriptaccess: "always"
	};
	var attributes = {
	  id: "playerLite",
	  name: "playerLite"
	};
	
	swfobject.embedSWF("swf/playerLite.swf", "play", flashvars.vidWidth, flashvars.vidHeight, "9.0.0","swf/expressInstall.swf", flashvars, params, attributes);

		}
		function forgotpass()
		{
			//alert("hi");
			$('#forgotpassword').jqxWindow('show');
		}
		function changepass()
		{
		 	$('#changepassword').jqxWindow('show');
		}
		
		
		var basicDemo = (function () {
            //Adding event listeners
            function _addEventListeners() {
                $('#resizeCheckBox').bind('change', function (event) {
                    if (event.args.checked) {
                        $('#window').jqxWindow('resizable', true);
                    } else {
                        $('#window').jqxWindow('resizable', false);
                    }
                });
                $('#dragCheckBox').bind('change', function (event) {
                    if (event.args.checked) {
                        $('#window').jqxWindow('draggable', true);
                    } else {
                        $('#window').jqxWindow('draggable', false);
                    }
                });
                $('#showWindowButton').click(function () {
                    $('#window').jqxWindow('show');
                });
                $('#hideWindowButton').click(function () {
                    $('#window').jqxWindow('hide');
                });
                $('#window').bind('resizing', function (event) {
                    $('#tab').jqxTabs('height', $('#windowContent').height() - 3);
                });
            };

            //Creating all page elements which are jqxWidgets
            function _createElements() {
                $('#showWindowButton').jqxButton({ theme: basicDemo.config.theme, height: '25px', width: '65px' });
                $('#hideWindowButton').jqxButton({ theme: basicDemo.config.theme, height: '25px', width: '65px' });
                $('#resizeCheckBox').jqxCheckBox({ theme: basicDemo.config.theme, height: '25px', width: '185px', checked: true });
                $('#dragCheckBox').jqxCheckBox({ theme: basicDemo.config.theme, height: '25px', width: '185px', checked: true });
                $('#restrictParentCheckBox').jqxCheckBox({ theme: basicDemo.config.theme, height: '25px', width: '185px', checked: true });
                $('#tab').jqxTabs({ height: 258, theme: basicDemo.config.theme });
            };

            //Creating the demo window
            function _createWindow() {
                $('#window').jqxWindow({ maxHeight: 400, maxWidth: 700, minHeight: 200, minWidth: 200, height: 200, width: 200, theme: basicDemo.config.theme });
            };

            return {
                config: {
                    dragArea: null,
                    theme: null
                },
                init: function () {
                    //Creating all jqxWindgets except the window
                    _createElements();
                    //Attaching event listeners
                    _addEventListeners();
                    //Adding jqxWindow
                    _createWindow();
                }
            };
        } ());

        $(document).ready(function () {
            var theme = $.data(document.body, 'theme', theme);
            if (theme == undefined) theme = '';

            //Setting demo's theme
            basicDemo.config.theme = theme;
            //Initializing the demo
            basicDemo.init();
        });
		
    </script>
</head>
<body background="../../images/stripe3.png">
<div class="headerBar">
	<div style='margin-left:20px'>
		<img src="../../images/Logo.png" id="logo" align="left"  />
	</div>
	<div >
		<input type='text' id='search'class='search'/>
		<input type='button' id='searchbutton' class='searchbutton'value="Search"/>
		<input type='button' id='uploadbutton' class='searchbutton' value="Upload"/>
		<div style='float:left'>
		<!--  login section -->
		<%
		HttpSession hs1=request.getSession();
	    if(hs1.getAttribute("emailid")==null)
	    { 
	    %>
			<div>
			<form action='../../auth' method='POST'>
			<table>
		
			
			<tr><td style="font-size:smaller;"> email</td><td style="font-size:smaller;"> Password</td>
			 </tr>
			 <tr>
			    <td><input type='text' id='emailid' name='emailid' /></td>
				<td> <input type='password' id='password' name='password' /></td>
				<td><input type='submit' id='sign' class='searchbutton'value="SignIN"/> </td>
				<td><input type='button' id='registration' class='searchbutton'value="Registration"/> </td>
			</tr>
			<tr> <td> <%if(request.getParameter("message")!=null)
			{
				out.print(request.getParameter("message"));
			}%></td>
					<td onclick="forgotpass()"><a href='#'>Forget Password</a></td>
					<td> </td>
					<td> </td>
			</tr>
				
				
				
				
			
			</table>
			</form>
			</div>
		 
		<div>
		<br/>
		</div>
		<%} 
	    else
	    {
	    	%>
		
			<input type='button' onclick='changepass()' value='change password' class='searchbutton'/>
			<input type='button' onclick="window.location.href='../../logout'" value='logout'  class='searchbutton'/>
	    	
	    	<%
	    }
		%>
	    	
		</div>
	</div>
</div>
<div id='container' style='width:100%'>
	<div id='leftcontainer' style='width:200px;float:left;margin-top:50px;margin-left:20px'>
		<div id='jqxWidget' style='width: 110px;'>
            <div id='jqxMenu' style="visibility: hidden;">
                <ul>
                    <li><a href="#">Hindi</a>
                    <ul>
                            <li><a href="#">Ghazal</a></li>
                            <li><a href="#">Folk</a></li>
                            <li><a href="#">Bhajan</a> </li>
                            <li><a href="#">Bollywood Song</a></li>
                            
                     </ul>
                     </li>
                    <li>Bengali
                        <ul>
                            <li><a href="#">Rabindra Sangit</a></li>
                            <li><a href="#">Nazrul Giti</a></li>
                            <li><a href="#">Bangla Rock</a> </li>
                            <li><a href="#">Tollywood Songs</a></li>
                            
                        </ul>
                    </li>
                    <li>English
                        <ul>
                            <li><a href="#">Pop</a></li>
                            <li><a href="#">Rock</a></li>
                            <li><a href="#">Jazz</a></li>
                            <li><a href="#">Hollywood Song</a></li>
                            
                        </ul>
                    </li>
                 </ul>
            </div>
         </div>
	</div>
	<div id='rightcontainer' style='margin-left:400px;margin-top:100px;width:800px;height:500px;border-bottom-width: thin;border-bottom-style: solid;border-top-color: #CCC;border-right-color: #CCC;border-bottom-color: #CCC;border-left-color: #CCC;background-color: gray;'>
		<div id='gridcontainer'>
		</div>
		
	</div>
</div>
<%
if(hs1.getAttribute("emailid")!=null)
{ 
	    %>	    
<div id='playercontianer' style='width:340px;height:350px;margin:25px;background-color:#EFEFF1;margin-top: -350px;'>

</div>

<%} %>

</body>
</html>
<div id='uploadcontainer'>
	<div>
	Upload Now
	</div>
	<div style='margin:40px'>
		<form enctype="multipart/form-data" action='../../AddMusic' method='post'>
		<br/>
		Language :<input type='text' name='language' id='language'/>
		<br/>
		Type :<input type='text' name='type' id='type'/>
		<br/>
		Title :<input type='text' name='title' id='title'/>
		<br/>
		Singer:<input type='text' name='singer' id='singer'/>
		<br/>
		Year  :<input type='text' id='year' name='year'/>
		<br/>
		<input type="file"  name="photo" />
		<br/>
		<input type='submit' id='uploadsubmit'/>
		
		</form>
	</div>

</div>
<div id='regcontainer'>
	<div>
		Register Now
	</div>
	<div>
		<div style='vertical-align: middle;width:60%'>
		<div style='height:25px'> Email ID: <input type='text' id='emailid' style="float:right"/>
		
		</div>
		<br/>
		<div style='height:25px'>Password: <input type='password' id='pass'style="float:right"/>
		
		</div>
		<br/>
		<div style='height:25px'>Confirm Password: <input type='password' id='password1' style="float:right"/>
		 
		</div>
		<br/>
		<div style='height:25px'>Secuirty Question : <input type='text' id='question' style="float:right"/>
		
		</div>
		<br/>
		<div style='height:25px'>Secuirty Answer : <input type='text' id='answer' style="float:right"/>
		
		</div>
		<br/>
		<input type='button' id='regbut' value='register' style="float:right"/>
		</div>
	</div>
</div>
<div id='forgotpassword'>
	<div>
		Forget Password
	</div>
	<div>
		<div style='vertical-align: middle'>
		<div>
		emailid : <input type='text' id='foremail'>
		</div>
		<br/>
		<div>
		<input type='button' id='chck' />
		Secuirty Question : <input type='text' id='forquestion'>
		</div>
		<br/>
		<div>
		Secuirty Answer : <input type='text' id='answer'>
		</div>
		<br/>
		<input type='button' id='regbut' value='submit'/>
		</div>
	</div>
</div>
<div id="changepassword">
	<div>
		change Password
	</div>
	<div>
		<div style='vertical-align: middle'>
		<div>
		pervious password : <input style='float:right' type='password' id='previouspass'>
		
		</div>
		<br/>
		<div>
		New Password : <input style='float:right' type='password' id='newpass'>
		</div>
		<br/>
		<div>
		Confirm New Password : <input style='float:right' type='password' id='newpass2'>
		</div>
		<br/>
		<input type='button' id='changepass' value='submit'/>
		</div>
	</div>
</div>
	 
		 <div id="jqxWidget">
        <div style="float: left;">
            <div>
                <input type="button" value="Show Online user" id="showWindowButton" />
                <input type="button" value="Hide Online user" id="hideWindowButton" style="margin-left: 5px" />
            </div>

        </div>
        <div style="width: 100%; height: 650px; margin-top: 50px;" id="mainDemoContainer">
            <div id="window">
                <div id="windowHeader">
                    <span>
                       Online People
                    </span>

                </div>
                <div style="overflow: hidden;" id="windowContent">
                    <div id="tab">
                        <ul style="margin-left: 30px;">
                            <li>
                                Currently Online
                            </li>
                        </ul>
                        <div>
                            <%
                           
                             ArrayList a=(ArrayList)getServletContext().getAttribute("CurrentUser");
                             for(Object i:a)
                              {%><li>
                                 <%=i.toString()%>
                              </li>
                          <%   }

                            %>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
		 