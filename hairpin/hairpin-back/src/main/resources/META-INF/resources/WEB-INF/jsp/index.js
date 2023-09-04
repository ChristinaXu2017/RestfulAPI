/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const modal = document.getElementById("myModal");
const closeBtn = document.getElementsByClassName("close")[0];
const popupTable = document.getElementById("popupTable");

// Add click event listener to close the modal
/*
closeBtn.addEventListener("click", function () {
    modal.style.display = "none";
});
 * 
 */

function createPopup(dataString) {
    modal.style.display = "block";
    const rowDataString = dataString.slice(0, -1);

    const rowDataArray = rowDataString.split(";").map(rowString => rowString.split("="));
    const rowData = rowDataArray.map(row => row.map(cell => cell.trim()));
    console.log(rowData);

    // Remove previous table if it exists
    popupTable.innerHTML = "";

    rowData.forEach(row => {
        // Check if the second column is "NA"
        if (row[1] !== "NA") {
            const tableRow = document.createElement("tr");
            row.forEach(cellData => {
                const tableCell = document.createElement("td");
                tableCell.textContent = cellData;
                tableRow.appendChild(tableCell);
            });
            popupTable.appendChild(tableRow);

            tableRow.addEventListener('click', function () {
                var clickedId = this.firstChild.textContent;
                submitafterpopup(clickedId);
                modal.style.display = "none";
            });
        }
    });
}



function generateTable() {
    modal.style.display = "block";
    const rowDataString = "Row 1, Column 1=Row 1, Column 2;Row 2, Column 1=Row 2, Column 2;Row 3, Column 1=Row 3, Column 2;".slice(0, -1);
    //rowDataString = rowDataString.substring(0, rowDataString.length - 1);
//    const rowDataString = rowDataString.slice(0, -1); 

    const rowDataArray = rowDataString.split(";").map(rowString => rowString.split("="));
    const rowData = rowDataArray.map(row => row.map(cell => cell.trim()));
    console.log(rowData);

    // Remove previous table if it exists
    popupTable.innerHTML = "";

    rowData.forEach(row => {
        const tableRow = document.createElement("tr");
        row.forEach(cellData => {
            const tableCell = document.createElement("td");
            tableCell.textContent = cellData;
            tableRow.appendChild(tableCell);
        });
        popupTable.appendChild(tableRow);

        tableRow.addEventListener('click', function () {
            var clickedId = this.firstChild.textContent;
            alert(clickedId);
        });
    });
}


function submit() {
    document.getElementById("submit").disabled = true;
    var gene_id = document.getElementById("gene").value;
    var formData = new FormData();
    var xhr = new XMLHttpRequest();
    var URL = 'submit_servlet';
    xhr.open('POST', URL, true);

    xhr.onload = function () {
        if (this.status === 200) {
            var image = document.getElementById('img_1');
            var idx = 0;
            var width = parseInt(xhr.responseText.substr(idx, 4));
            image.style.width = width;

            idx = idx + 4;
            image.style.height = width / 2;

            const myArray = xhr.responseText.substr(idx).split("#");

            if (myArray[0] === "noimage") {
                alert("This cromosome dosent exist. Please choose another from the following");
                const cromosomechoices = myArray[1];
                createPopup(cromosomechoices);
            } else {
                image.src = "data:image/png;base64," + myArray[0];

                // Get the data string
                const data = myArray[1];

                let infoTable = document.getElementById("infoTable");

                if (infoTable) {
                    alert("Gene found!");
                    infoTable.remove(); // remove existing info table.
                }

                infoTable = document.createElement("table");
                infoTable.id = "infoTable"; // give the table a specific ID

                let rows = data.split(" ");
                let headerRow = infoTable.insertRow(0);
                let headerCell1 = headerRow.insertCell(0);
                let headerCell2 = headerRow.insertCell(1);
                let headerCell3 = headerRow.insertCell(2);
                headerCell1.textContent = "Tissue";
                headerCell2.textContent = "TPM";
                headerCell3.textContent = "NumReads";

                for (let i = 0; i < rows.length - 1; i++) {
                    let row = infoTable.insertRow(-1);
                    let cells = rows[i].split(",");
                    for (let j = 0; j < cells.length; j++) {
                        let cell = row.insertCell(-1);
                        cell.textContent = cells[j];
                    }
                }

                document.body.appendChild(infoTable);
            }
            document.getElementById("submit").disabled = false;
        }
    };
    formData.set("param", "1");
    formData.set("gene_id", gene_id);
    xhr.send(formData);
}


function submitafterpopup(id) {
    document.getElementById("submit").disabled = true;
    var gene_id = id;
    document.getElementById("gene").value = gene_id;
    var formData = new FormData();
    var xhr = new XMLHttpRequest();
    var URL = 'submit_servlet';
    xhr.open('POST', URL, true);

    xhr.onload = function () {
        if (this.status === 200) {
            var image = document.getElementById('img_1');
            var idx = 0;
            var width = parseInt(xhr.responseText.substr(idx, 4));
            image.style.width = width;

            idx = idx + 4;
            image.style.height = width / 2;

            const myArray = xhr.responseText.substr(idx).split("#");

            if (myArray[0] === "noimage") {
                alert("This cromosome dosent exist. Please choose another from the following");
                const cromosomechoices = myArray[1];
                createPopup(cromosomechoices);
            } else {
                alert("Gene found!");
                image.src = "data:image/png;base64," + myArray[0];

                // Get the data string
                const data = myArray[1];

                let infoTable = document.getElementById("infoTable");

                if (infoTable) {
                    infoTable.remove(); // remove existing info table
                }

                infoTable = document.createElement("table");
                infoTable.id = "infoTable"; // give the table a specific ID

                let rows = data.split(" ");
                let headerRow = infoTable.insertRow(0);
                let headerCell1 = headerRow.insertCell(0);
                let headerCell2 = headerRow.insertCell(1);
                let headerCell3 = headerRow.insertCell(2);
                headerCell1.textContent = "Tissue";
                headerCell2.textContent = "TPM";
                headerCell3.textContent = "NumReads";

                for (let i = 0; i < rows.length - 1; i++) {
                    let row = infoTable.insertRow(-1);
                    let cells = rows[i].split(",");
                    for (let j = 0; j < cells.length; j++) {
                        let cell = row.insertCell(-1);
                        cell.textContent = cells[j];
                    }
                }

                document.body.appendChild(infoTable);
            }
            document.getElementById("submit").disabled = false;
        }
    };
    formData.set("param", "1");
    formData.set("gene_id", gene_id);
    xhr.send(formData);
}



function submitGenome() {
    document.getElementById("submitgeneid").disabled = true;
    var gene_id = document.getElementById("id_number").value;
    var toggleSwitch = document.getElementById("toggleSwitch").value;
    var formData = new FormData();
    var xhr = new XMLHttpRequest();
 //   var URL = 'submit_servlet_genome';
 	var URL = 'miRNA'
    xhr.open('POST', URL, true);

    xhr.onload = function () {
	//debug
alert("This id dosent exist.");

        if (this.status === 200) {
            var image = document.getElementById('img_1_gene');
            var idx = 0;
            var width = parseInt(xhr.responseText.substr(idx, 4));
            image.style.width = width;

            idx = idx + 4;
            image.style.height = 10000;

            const myArray = xhr.responseText.substr(idx).split("#");

            if (myArray[0] === "noimage") {
                alert("This id dosent exist.");
            } else {
                image.src = "data:image/png;base64," + myArray[0];

                // Get the data string
                const data = myArray[1];
                
            }
            document.getElementById("submitgeneid").disabled = false;
        }
    };
    formData.set("param", "1");
    formData.set("toggle_switch", toggleSwitch);
    formData.set("gene_id", gene_id);
    xhr.send(formData);
}


		function convertToImage(imageValue) {   
			alert("convertToImage(imageValue)!");
   
            if (this.status === 200) {
            	
            	var imageElement = document.createElement("img_1_gene");
                var idx = 0;
                var width = parseInt(imageValue.substr(idx, 4));
                imageElement.style.width = width;
                idx = idx + 4;
                imageElement.style.height = 10000;
       	

                const myArray = imageValue.substr.substr(idx).split("#");

                if (myArray[0] === "noimage") {
                    alert("This id dosent exist.");
                } else {
                    image.src = "data:image/png;base64," + myArray[0];

                    // Get the data string
                    const data = myArray[1];
                    
                }
                document.body.appendChild(imageElement);
                 
            }
   		}
