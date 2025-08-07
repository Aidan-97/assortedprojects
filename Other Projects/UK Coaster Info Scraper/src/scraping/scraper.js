const request = require('request');
const cheerio = require('cheerio');
const fs = require('fs');

var coasterInfoTable = [];

for (let i=1; i<10; i++){
    request.get({gzip: true, encoding: "utf-8", uri: "https://rcdb.com/r.htm?page=" + i + "&st=93&ot=2&ol=26828"}, function (error, response, body) {
        if (!error && response.statusCode == 200){
            const $ = cheerio.load(body);

            $('tr').each( (j, elem) => {
                if (elem.childNodes.length == 6 && elem.parent.name !== 'thead'){
                    const name = $(elem.childNodes[1]).find('a').text();
                    
                    const park = $(elem.childNodes[2]).find('a').text();
                    
                    const type = $(elem.childNodes[3]).find('a').text();

                    const design = $(elem.childNodes[4]).find('a').text();

                    const time = $(elem.childNodes[5]).find('time').attr('datetime');
        
                    var coasterInfo = {name: name, park: park, type: type, design: design, time: time};
                    
                    coasterInfoTable.push(coasterInfo);
                }
            })
        }
    });
}

setTimeout( () => fs.writeFile('./coaster-info.json', JSON.stringify(coasterInfoTable, null, 4), (err) => {}), 3000 )