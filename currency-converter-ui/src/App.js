import React, { useEffect, useState } from "react";
import Selection from "./Selection";

function App() {
    const [currencies, setCurrencies] = useState([]);
    const [names, setCurrencyNames] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);
    const [currencyAmount, setCurrencyAmount] = useState(0);
    const [conversionResult, setConversionResult] = useState(" ");
    const [selectionOne, setSelectionOne] = useState();
    const [selectionTwo, setSelectionTwo] = useState();

    const getConversionResult = () => {
        fetch(
            "http://localhost:8080/convertFrom?" +
                new URLSearchParams({
                    currencyOne: selectionOne,
                    currencyTwo: selectionTwo,
                    valueOne: currencyAmount,
                })
        )
            .then((res) => {
                console.log("response : ", res);
                return res.json();
            })
            .then((json) => {
                console.log("json response : ", json);
                if (json["code"] === 0) {
                    setConversionResult(json["data"]);
                } else {
                    console.log("json response : ", json);
                }
            });
    };

    useEffect(() => {
        fetch("http://localhost:8080/currencies")
            .then((res) => {
                return res.json();
            })
            .then((json) => {
                console.log(json);
                if (json["code"] === 0) {
                    setCurrencies(Object.keys(json["data"]));
                    setCurrencyNames(Object.values(json["data"]));
                    setIsLoaded(true);
                    setSelectionOne(currencies[0]);
                    setSelectionTwo(currencies[0]);
                } else {
                    setCurrencies(["Could not load data"]);
                }
            });
    }, []);

    return (
        <>
            <input
                disabled={!isLoaded}
                onChange={(e) => {
                    setCurrencyAmount(e.target.value);
                }}
                value={currencyAmount}
            />

            <Selection
                lable="Convert From Currency"
                options={currencies}
                values={names}
                selectedValue={selectionOne}
                setSelectedValue={setSelectionOne}
            />
            <br />
            <br />
            <Selection
                lable="Convert To Currency"
                options={currencies}
                values={names}
                selectedValue={selectionTwo}
                setSelectedValue={setSelectionTwo}
            />

            <button
                disabled={currencyAmount.toString() === "0"}
                onClick={getConversionResult}
            >
                {" "}
                Find Exchange Amount{" "}
            </button>
            <div>
                {" "}
                {`converted amount => `} {conversionResult}{" "}
            </div>
        </>
    );
}

export default App;
