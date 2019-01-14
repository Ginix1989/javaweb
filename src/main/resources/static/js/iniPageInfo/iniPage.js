/*<![CDATA[*/
function readMenu (menuinfo) {
    var menuarray = menuinfo;
    $.each(menuarray, function (i, parameters) {
        if (parameters.parentId == 0) {
            menuLiId = 'myMenu' + parameters.id;
            $("#mySearch").after("<li><a href='javascript:(0)' data-toggle='collapse' data-target='#" + menuLiId + "'>" + parameters.menuName + "<span class='" + parameters.menuClass + "'></span></a><ul id='" + menuLiId + "' class='nav collapse'></ul></li>");
            return true;
        }
        $("#" + menuLiId + "").append("<li> <a href='"+parameters.menuUrl+"'><span class='" + parameters.menuClass + "'></span> &nbsp;" + parameters.menuName + "</a></li>");
    });
}

/*]]>*/