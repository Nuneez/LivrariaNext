
function createInput(type, value, attributes){
    var input = document.createElement("input");
    input.type = type;
    input.value = value;
    setAttributes(input, attributes);
        
    return input;    
};

function createTd(width, value, attributes){
    var td = document.createElement("td");
    if (width)
        td.style["width"] = width;
    
    setAttributes(td, attributes);
    
    if (value)
        td.innerHTML = value;
    
    return td;
};

function createTdWithInput(input, width, value, attributes){
    var td = createTd(width, value, attributes);
    if (input)
    {
        var i = createInput(input.type, input.value, input.attributes);
        td.appendChild(i);        
    }
    return td;
};

function createTr(tds, attributes){
    var tr = document.createElement("tr");
    
    tds.forEach(td => tr.appendChild(createTdWithInput(td.input, td.width, td.value, td.attributes)));
    
    setAttributes(tr, attributes);
    
    return tr;
};

function setAttributes(obj, attributes){
    if (attributes)
        attributes.forEach(a => obj.setAttribute(a.name, a.value));
}
