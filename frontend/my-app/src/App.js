import './App.css';
import React, {useState} from "react";

const tableStyle = {
    borderCollapse: 'collapse',
    margin: 'auto'
}

const tdStyle = {
    border: '1px solid black',
};

const App = () => {

    const [rowsData, setRowsData] = useState(
        [{id: ' ', name: ' ', country: ' ', birthDate: ' '}]
    )

    const [from, setFrom] = useState(0)
    const [to, setTo] = useState(4)

    const [editedId, setEditedId] = useState(rowsData.id)

    const [addedRow, setAddedRow] = useState({id: '', name: '', country: '', birthDate: ''})

    const [editedRow, setEditedRow] = useState({id: '', name: '', country: '', birthDate: ''})

    const [rowAddingMode, setRowAddingMode] = useState(false)

    const addTableRows = () => {
        setRowAddingMode(true)
    }

    if(to > rowsData.length) {
        setTo(rowsData.length)
    }

    //getAll Method
    //TODO automate the 'refresh' process => useEffect()
    const refresh = () => {
        console.log(from, to)
        fetch(`http://localhost:9988/api/authors?from=${from}&to=${to}` , {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }
        ).then(response => response.json())
         .then(json => setRowsData(json));
        console.log('hello from getAll')
        console.log(from, to)
    }

    //delete Method
    const deleteTableRows = (id) => {
        fetch('http://localhost:9988/api/authors/' + id, {
                method: 'DELETE',
            }
        ).then(() => {
            setRowsData(rowsData.filter(row => row.id !== id))
        })
         .then(() => console.log("Deletion completed"))
    }

    //post Method
    const saveInRowAddingMode = () => {
        fetch('http://localhost:9988/api/authors', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(addedRow)
            }
        ).then(response => response.json())
         .then(json => setRowsData( [...rowsData, json]))
        console.log('hello from add')
        setRowAddingMode(false)
    }


    //put method
    const saveInRowEditingMode = (editedId) => {
        fetch('http://localhost:9988/api/authors/' + editedId, {
                method: "PUT",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(editedRow)
            }
        ).then(response => response.json())
         .then(updatedAuthor => {
            setRowsData(
                rowsData.map((rowData) => {
                    return rowData.id === editedId ? updatedAuthor : rowData
                }
            ))
            console.log(rowsData)
            console.log(updatedAuthor)
            setEditedId('')
        })
    }

    const enableRowEditingMode = (rowData) => {
        setEditedId(rowData.id)
        setEditedRow(rowData)
        console.log("enabled")
    }

    const disableRowEditingMode = () => {
        setEditedId('')
        console.log("disabled")
    }

    //getALL filteredByName
    const filteredByName = (name) => {
        fetch('http://localhost:9988/api/authors' + (name ? `?name=${name}` : ''), {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            }
        ).then(response => response.json())
         .then(json => setRowsData(json));
        console.log('hello from getAll')
    }

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
                            <th><button  onClick={addTableRows} >Add</button></th>
                            <th><button onClick={refresh}>Refresh</button></th>
                            <th>Filter <input
                                onKeyPress={(e) => {
                                    if (e.key === "Enter") {
                                        filteredByName(e.target.value)
                                        }
                                    }
                                }
                                />
                            </th>

                            <th><button onClick={() => {setFrom(from - 5); setTo(to - 5)}}> Back </button></th>
                            <th><button onClick={() => {setFrom(from + 5); setTo(to + 5)}}> Forward </button></th>

                        </tr>

                        {rowAddingMode ?
                            <tr>
                                <td style={tdStyle}>{rowsData.id}</td>
                                <td style={tdStyle}><input onChange={e => {setAddedRow({...addedRow, name: e.target.value})}}/></td>
                                <td style={tdStyle}><input onChange={e => {setAddedRow({...addedRow, birthDate: e.target.value})}}/></td>
                                <td style={tdStyle}><input onChange={e => {setAddedRow({...addedRow, country: e.target.value})}}/></td>
                                <td style={tdStyle}>
                                    <button onClick={saveInRowAddingMode}>Save</button>
                                    <button onClick={() => {setRowAddingMode(false)}}>Cancel</button>
                                </td>
                            </tr> : <br/>
                        }

                        {rowsData.map(rowsData =>
                            <tr key={rowsData.id}>
                                        <td style={tdStyle}>{rowsData.id}</td>
                                        <td style={tdStyle}>{editedId === rowsData.id ? <input defaultValue={rowsData.name} onChange={e => {setEditedRow({...editedRow, name: e.target.value})}}/> : rowsData.name}</td>
                                        <td style={tdStyle}>{editedId === rowsData.id ? <input defaultValue={rowsData.birthDate} onChange={e => {setEditedRow({...editedRow, birthDate: e.target.value})}}/> : rowsData.birthDate}</td>
                                        <td style={tdStyle}>{editedId === rowsData.id ? <input defaultValue={rowsData.country} onChange={e => {setEditedRow({...editedRow, country: e.target.value})}}/> : rowsData.country}</td>
                                <td>
                                    {editedId !== rowsData.id ? // not in editing mode
                                    <>
                                            <button onClick={() => enableRowEditingMode(rowsData)}>Edit</button>
                                            <button onClick={() => deleteTableRows(rowsData.id)} >Delete</button>
                                    </>
                                        :                       // editing mode
                                    <>
                                            <button onClick={() => saveInRowEditingMode(rowsData.id)}>Save</button>
                                            <button onClick={disableRowEditingMode}>Discard changes</button>
                                    </>}
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