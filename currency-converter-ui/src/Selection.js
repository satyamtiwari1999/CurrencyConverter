import React from "react";

export default function Selection({
    options,
    values,
    lable,
    selectedValue,
    setSelectedValue,
}) {
    return (
        <>
            <div> {lable} </div>
            <select
                value={selectedValue}
                onChange={(e) => {
                    setSelectedValue(e.target.value);
                }}
            >
                {options.map((code, index) => {
                    return (
                        <option key={index} value={code}>
                            {" "}
                            {`${values[index]} (${code})`}{" "}
                        </option>
                    );
                })}
            </select>
        </>
    );
}
