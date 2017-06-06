/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ajaxGet(url, success){
    var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xhr.withCredentials = true;
    xhr.open('GET', url);
    xhr.onreadystatechange = function() {
    if (xhr.readyState>3 && xhr.status==200) success(xhr.responseText);
    };
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhr.setRequestHeader('credentials', 'same-origin');
    xhr.send();
    return xhr;
};

function prepareData(data)  {
  if (typeof data === 'object') {
    return JSON.stringify(data);
  } else {
    return data;
  }
}

function ajaxPost(url, data, success) {
    var params = typeof data == 'string' ? data : Object.keys(data).map(
            function(k) { return encodeURIComponent(k) + '=' + encodeURIComponent(prepareData(data[k])) }
        ).join('&');

    var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    xhr.withCredentials = true;
    xhr.open('POST', url);
    xhr.onreadystatechange = function() {
        if (xhr.readyState>3 && xhr.status==200) { success(xhr.responseText); }
    };
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhr.setRequestHeader('credentials', 'same-origin');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(params);
    return xhr;
}
