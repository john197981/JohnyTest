
var include_num = 0;
var bold = 0;
var s = new Array();
s[0] = "Help^Help.html^Welcome to help page for My Forum!...^Help`;";
s[1] = "Discussion Forum^OIFM_User_1_Discussion_Forum.html^Discussion Forum Discussion Forum allows interactions between users. Discussion threads are gr...^`;";
s[2] = "Consultation Paper^OIFM_User_2_Consultation_Paper.html^Consultation Paper Consultation papers are published by the administrators to get feedbacks on...^Consultation Paper`;";
s[3] = "Survey^OIFM_User_3_Survey.html^Survey Surveys are conducted to get feedbacks on a particular subject in a structured manner. ...^Survey`;";
s[4] = "Admin Main Page^OIFM_Main_Admin_1_Admin_Main_Page.html^Admin Main Page a.  After logged in as an administrator, the administrator module is accessibl...^Admin Main Page`;";
s[5] = "Maintain Categories, Boards, and Topics^OIFM_Main_Admin_2_Category_Board.html^Maintain Categories, Boards, and Topics From this page, you can create / modify / delete categ...^Maintain Categories, Boards, and Topics`;";
s[6] = "Threads Moderation^OIFM_Div_Admin_Threads_Moderation.html^Threads Moderation Threads created with “Moderation required” setting will be listed on this p...^Threads Moderation`;";
s[7] = "Postings Moderation^OIFM_Div_Admin_3_Postings_Moderation.html^Postings Moderation Postings posted with “Moderation required” setting will be listed on this ...^Postings Moderation`;";
s[8] = "Consultation Paper^OIFM_Div_Admin_5__Consultation_Paper.html^Consultation Paper My Forum allows administrators to publish papers on policies and issues and...^Consultation Paper`;";
s[9] = "Survey^OIFM_Div_Admin_6__Survey.html^Survey My Forum allows divisions to conduct surveys to get feedback on a particular subject in...^Survey`;";
s[10] = "Polls^OIFM_Main_Admin_3_Poll.html^Poll Polls can be used to gather quick feedback from users on a certain topic. Polls will be d...^Polls`;";
s[11] = "Roles^OIFM_Main_Admin_4_Roles.html^Roles Each user of My Forum will be assigned a role, which determines the access permission to...^Roles`;";
s[12] = "User Profile^OIFM_Div_Admin_7_User_Profile.html^User Profile Administrators can search, view, and modify some details of user profiles. a....^User Profile`;";
s[13] = "Code Master^OIFM_Main_Admin_6_Code_Master.html^Code Master The Code Master allows administrators to maintain various common codes used throug...^Code Master`;";
s[14] = "Configuration^OIFM_Main_Admin_7_Configuration.html^Configuration From the Configuration page, administrator can set various setting of the system...^Configuration`;";
s[15] = "User Ranking^OIFM_Main_Admin_8_Ranking.html^User Ranking From the User Ranking module, most active users on a certain period of time can b...^User Ranking`;";
s[16] = "User Groups^OIFM_Div_Admin_8_User_Groups.html^User Groups Administrators can create user groups to help in sending invitation mails for priv...^User Groups`;";
s[17] = "Audit Trail^OIFM_Main_Admin_9_Audit_Trail.html^Audit Trail Audit Trail displays trails of moderations on threads and postings made by adminis...^Audit Trail`;";
s[18] = "Discussion Forum Administrative Functions^OIFM_Div_Admin_9_DiscForum_Funcs.html^Discussion Forum Administrative Functions Some administrative functions for the discussion for...^Discussion Forum Administrative Functions`;";

var cookies = document.cookie;
var p = cookies.indexOf("d=");
if (p != -1) {
 var st = p + 2;
 var en = cookies.indexOf(";", st);
 if (en == -1) {
   en = cookies.length;
 }
 var d = cookies.substring(st, en);
d = unescape(d);
}
var od = d;
var m = 0;
if (d.charAt(0) == '"' && d.charAt(d.length - 1) == '"') {
 m = 1;
}
var r = new Array();
var co = 0;
if (m == 0) {
 var woin = new Array();
 var w = d.split(" ");
 for (var a = 0; a < w.length; a++) {
   woin[a] = 0;
   if (w[a].charAt(0) == '-') {
     woin[a] = 1;
   }
 }
for (var a = 0; a < w.length; a++) {
 w[a] = w[a].replace(/^\-|^\+/gi, "");
}
a = 0;
for (var c = 0; c < s.length; c++) {
 pa = 0;
 nh = 0;
 for (var i = 0; i < woin.length; i++) {
   if (woin[i] == 0) {
     nh++;
     var pat = new RegExp(w[i], "i");
     var rn = s[c].search(pat);
     if (rn >= 0) {
       pa++;
     } else {
       pa = 0;
     }
   }
     if (woin[i] == 1) {
       var pat = new RegExp(w[i], "i");
       var rn = s[c].search(pat);
       if (rn >= 0) {
         pa = 0;
       }
     }
   }
   if (pa == nh) {
     r[a] = s[c];
     a++;
   }
 }
 co = a;
}
if (m == 1) {
 d = d.replace(/"/gi, "");
 var a = 0;
 var pat = new RegExp(d, "i");
 for (var c = 0; c < s.length; c++) {
   var rn = s[c].search(pat);
   if (rn >= 0) {
     r[a] = s[c];
     a++;
   }
 }co = a;
}
function return_query() {
 document.jse_Form.d.value = od;
}
function num_jse() {
 document.write(co);
}
function out_jse() {
 if (co == 0) {
   document.write('Your search did not match any documents.<br>Please ensure that all keywords are spelled correctly.<br>Or try different or more general keywords. Thank you.');
   return;
   }
   for (var a = 0; a < r.length; a++) {
     var os = r[a].split("^");
     if (bold == 1 && m == 1) {
       var br = "<b>" + d + "</b>";
       os[2] = os[2].replace(pat, br);
     }
     if (include_num == 1) {
       document.write(a + 1, '. <a href="', os[1], '">', os[0], '</a><br>', os[2], '<p>');
     } else {document.write('<a href="', os[1], '">', os[0], '</a><br>', os[2], '<p>');
     }
   }
}