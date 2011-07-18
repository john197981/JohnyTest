//© DevHost Ltd, 2005 v1.6

function OpenFileRelativeToCHMFolder(stFileName)
{
  var X, Y, sl, a, ra, link;
  ra = /:/;
  a = location.href.search(ra);
  if (a == 2)
    X = 14;
  else
    X = 7;
  sl = "\\";
  Y = location.href.lastIndexOf(sl) + 1;
  link = 'file:///' + location.href.substring(X, Y) + stFileName;
  location.href = link;
}

function FHToggleHiddenParagraphs(iParagraph, iImage, stHidden, stVisible)
{if (iParagraph.style.display=="none")
  {iParagraph.style.display="";
   iImage.src=stVisible;
  }
else 
  {iParagraph.style.display="none";
   iImage.src=stHidden;
  }
}