
var include_num = 0;
var bold = 0;
var s = new Array();
s[0] = "Help^Help.html^Welcome to help page for My Forum!...^Help`;";
s[1] = "Discussion Forum^OIFM_User_1_Discussion_Forum.html^Discussion Forum Discussion Forum allows interactions between users. Discussion threads are gr...^`;";
s[2] = "Consultation Paper^OIFM_User_2_Consultation_Paper.html^Consultation Paper Consultation papers are published by the administrators to get feedbacks on...^Consultation Paper`;";
s[3] = "Survey^OIFM_User_3_Survey.html^Survey Surveys are conducted to get feedbacks on a particular subject in a structured manner. ...^Survey`;";
s[4] = "Admin Main Page^OIFM_Div_Admin_1_Admin_Mainpage.html^Admin Main Page a.  After logged in as an administrator, the administrator module is accessibl...^Admin Main Page`;";
s[5] = "Threads Moderation^OIFM_Div_Admin_Threads_Moderation.html^Threads Moderation Threads created with “Moderation required” setting will be listed on this p...^Threads Moderation`;";
s[6] = "Postings Moderation^OIFM_Div_Admin_3_Postings_Moderation.html^Postings Moderation Postings posted with “Moderation required” setting will be listed on this ...^Postings Moderation`;";
s[7] = "Maintain Categories, Boards, and Topics^OIFM_Div_Admin_4__Category_Board.html^Maintain Categories, Boards, and Topics From this page, you can create / modify / delete categ...^Maintain Categories, Boards, and Topics`;";
s[8] = "Consultation Paper^OIFM_Div_Admin_5__Consultation_Paper.html^Consultation Paper My Forum allows administrators to publish papers on policies and issues and...^Consultation Paper`;";
s[9] = "Survey^OIFM_Div_Admin_6__Survey.html^Survey My Forum allows divisions to conduct surveys to get feedback on a particular subject in...^Survey`;";
s[10] = "User Profile^OIFM_Div_Admin_7_User_Profile.html^ User Profile Administrators can search, view, and modify some details of user profiles. ?...^User Profile`;";
s[11] = "User Groups^OIFM_Div_Admin_8_User_Groups.html^[$#BOOK_Toc113864544]User Groups Administrators can create user groups to help in sending invi...^User Groups`;";
s[12] = "Discussion Forum Administrative Functions^OIFM_Div_Admin_9_DiscForum_Funcs.html^[$#BOOK_Toc113864552]Discussion Forum Administrative Functions Some administrative functions f...^Discussion Forum Administrative Functions`;";
s[13] = "Admin Main Page^AdminMainPage.html^...^Admin Main Page`;";
s[14] = "Maintain Categories, Boards, and Topics^MaintainCategoriesBoardsAndTopics.html^...^Maintain Categories, Boards, and Topics`;";
s[15] = "Poll^Poll.html^...^Poll`;";
s[16] = "Roles^Roles.html^...^Roles`;";
s[17] = "User Profile^UserProfile.html^...^User Profile`;";
s[18] = "Code Master^CodeMaster.html^...^Code Master`;";
s[19] = "Configuration^Configuration.html^...^Configuration`;";
s[20] = "User Ranking^UserRanking.html^...^User Ranking`;";
s[21] = "Audit Trail^AuditTrail.html^...^Audit Trail`;";
s[22] = "Threads Moderation^ThreadsModeration.html^...^Threads Moderation`;";
s[23] = "Postings Moderation^PostingsModeration.html^...^Postings Moderation`;";
s[24] = "Consultation Paper^ConsultationPaper1.html^...^Consultation Paper`;";
s[25] = "Survey^Survey1.html^...^Survey`;";
s[26] = "User Groups^UserGroups.html^...^User Groups`;";
s[27] = "Discussion Forum Administrative Functions^DiscussionForumAdministrativeFunctions.html^...^Discussion Forum Administrative Functions`;";

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