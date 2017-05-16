/**
 * Insere um element antes do elemento referencia
 * @param  {Object} newElement       Referencia ao elemento html
 * @param  {Object} referenceElement Referencia ao elemento html
 * @return {Void}
 */
function insertBefore(newElement, referenceElement) {
  referenceElement.parentNode.insertBefore(newElement, referenceElement);
}

/**
 * Insere um element depois do elemento referencia
 * @param  {Object} newElement       Referencia ao elemento html
 * @param  {Object} referenceElement Referencia ao elemento html
 * @return {Void}
 */
function insertAfter(newElement, referenceElement) {
  var nextElement = referenceElement.nextElementSibling;
  insertBefore(newElement, nextElement);
}

/**
 * Adiciona as opções a lista de opções
 * @param  {Array} opcoes Lista de objetos para colocar na lista de opções para aparecer no html
 * @param  {Object} element Referencia ao elemento html
 * @return {Void}
 */
function activatedLiveSearch(options, element) {
  var liveSearchAttr = element.getAttribute("live-search");
  if (Array.isArray(options)) {
    if (document.querySelector('datalist#' + liveSearchAttr + '-options')) {
      document.querySelectorAll('datalist#' + liveSearchAttr + '-options')
      .forEach(function(element) {
        element.remove();
      });
    }

    element.setAttribute("list", liveSearchAttr + '-options');
    var datalist = document.createElement("datalist");
    datalist.setAttribute("id", liveSearchAttr + '-options');
    insertAfter(datalist, element);
    options.forEach(function(option) {
      var elOption = document.createElement("option");
      Object.keys(option).forEach(function(op) {
        if (op === 'id') {
          elOption.setAttribute("value", option.id);
        } else if(op === 'name') {
          elOption.setAttribute("name", option.name);
          elOption.append(option.name);
        } else {
          elOption.setAttribute(op, option[op]);
        }
        datalist.appendChild(elOption);
      })
    });
  } else {
    console.error("Erro ao adicionar montar lista de opções.");
  }
};

/**
 * Adiciona todos os elementos live-search para ações padrão e
 * @param  {Object} element Referencia ao elemento html
 * @param  {Funcion} callbacks Metodos que devem ser chamados quando o live-search for acionado.
 *                            O array que for colocado no retorno para o .
 *                            O retorno do callback será representado como r
 *                            exemplo: <option value="r.value">r.id - r.name</option>
 * @return {Function} Metodo adicionado ao listener.
 */
function activeLiveSearch(element, callbacks) {
  return function() {
    var attr = element.getAttribute('live-search');
    if (typeof callbacks[attr] != undefined && typeof callbacks[attr] != null) {
      callbacks[attr](element, activatedLiveSearch);
    }
  };
};

/**
 * Adiciona todos os elementos live-search para ações padrão e
 * @param  {Object} callbacks Metodos que devem ser chamados quando o live-search for acionado
 * @return {Void}
 */
function addLiveSearchListeners(callbacks) {
  var selectors = document.querySelectorAll('[live-search]');
  selectors.forEach(function(selector) {
    selector.addEventListener("keyup", activeLiveSearch(selector, callbacks));
  });
};

/**
* Inicializa o live-search
 * @param  {Object} keyDownCallBack Metodos que devem ser chamados quando o live-search for acionado
 * @return {Void}
 */
window.liveSearch = function (keyDownCallBack) {
  addLiveSearchListeners(keyDownCallBack);
};
