import './App.css';
import React, {useState} from "react";
import {Input} from "semantic-ui-react";

const tableStyle = {
    borderCollapse: 'collapse',
    margin: 'auto'
}

const tdStyle = {
    border: '1px solid black',
};

const App = () => {

    const refresh = () => {
        fetch('http://localhost:9988/api/authors', {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }
        )
            .then(response => response.json())
            .then(json => setRowsData(json));
        console.log('hello from getAll')
    }

    function onUpdateAuthor(updatedAuthor) {
        const updatedAuthors = rowsData.map(
            author => {
                if (author.id === updatedAuthor.id) {
                    return updatedAuthor
                } else {return author}
            }
        )
        setRowsData(updatedAuthor)
    }

    function Authors({authors, onUpdateAuthor}) {

        const [isEditing, setIsEditing] = useState(false);

        const [edit, setEdit] = useState(
            [{id: ' ', name: ' ', birthDate: ' ', country: ' '}]
        )
    }

    const [rowsData, setRowsData] = useState(
        [{id: ' ', name: ' ', birthDate: ' ', country: ' '}]
    )

    const deleteTableRows = () => {
        const rows = [...rowsData];
        rows.splice(rowsData.id, 1);
        setRowsData(rows);
    }

    const handleChange = (index, event) => {

        const {name, value} = event.target;
        const rowsInput = [...rowsData];
        rowsInput[index][name] = value;
        setRowsData(rowsInput);
    }

    const addTableRows = () => {
        setRowsData([...rowsData, {id: ' ', name: ' ', country: ' ', birthDate: ' '}])
    }

    const add = () => {
        fetch('http://localhost:9988/api/authors', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    firstParam: rowsData.id,
                    secondParam: <input defaultValue={rowsData.name}/>,
                    thirdParam: rowsData.country,
                    fourthParam: rowsData.birthDate
                })
            }
        )   .then(response => response.json())
            .then(json => setRowsData(json))
        console.log('hello from add')
    }


    const [editedId, setEditedId] = useState('')

    const [editedName, setEditedName] = useState('')

    const [editedCountry, setEditedCountry] = useState('')

    const [editedBirthDate, setEditedBirthDate] = useState('')

    return(
        <div className="container">
            <div className="row">
                <div className="col-sm-8">
                    <table style={tableStyle}>
                    <tbody className="table" style={tableStyle}>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Date of Birth</th>
                            <th>Country</th>
                            <th><button  onClick={add} >+</button></th>
                            <button onClick={()=>(deleteTableRows(rowsData.id))}>x</button>
                            <button onClick={refresh}>Refresh</button>
                        </tr>
                        {rowsData.map(rowsData =>
                            <tr key={rowsData.id}>
                                <td style={tdStyle}>{rowsData.id}</td>
                                <td style={tdStyle}>{editedName === rowsData.name ? <input defaultValue={'Max Mustermann'}/> : rowsData.name}</td>
                                <td style={tdStyle}>{editedBirthDate === rowsData.birthDate ? <input defaultValue={'2022-11-11'}/> : rowsData.birthDate}</td>
                                <td style={tdStyle}>{editedCountry === rowsData.country ? <input defaultValue={'DE'}/> : rowsData.country}</td>
                                <td>
                                    <button onClick={() => {setEditedId(rowsData.id); setEditedName(rowsData.name); setEditedCountry(rowsData.country); setEditedBirthDate(rowsData.birthDate)}}>Edit</button>
                                    <button onClick={() => {setEditedId(''); setEditedName(''); setEditedCountry(''); setEditedBirthDate('')}}>Cancel</button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default App