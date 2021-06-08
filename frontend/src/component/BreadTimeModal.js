import { useEffect } from "react";
import { Table } from "react-bootstrap";
import "../css/BreadTimeModal.css";

const BreadTimeModal = (props) => {
  const { open, close, bakeryData, breadData } = props;

  const viewBreadTimeTable = () => {
    if (!open) return;

    const result = [];
    result.push(
      <tr>
        <th>빵 이름</th>
        <th>나오는 시간</th>
        <th>가격</th>
      </tr>
    );
    for (let i = 0; i < breadData.length; i++) {
      result.push(
        <tr>
          <td>{breadData[i].foodName}</td>
          <td>{breadData[i].saleTime}</td>
          <td>{breadData[i].price}</td>
        </tr>
      );
    }
    return result;
  };

  return (
    //modal이 열릴 때 openModal 클래스가 생성된다.
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section>
          <header>
            {bakeryData.storeName}
            <button className="close" onClick={close}>
              &times;
            </button>
          </header>
          <main>
            <Table striped bordered size="sm">
              {viewBreadTimeTable()}
            </Table>
          </main>
          <footer>
            <button className="close" onClick={close}>
              &times;
            </button>
          </footer>
        </section>
      ) : null}
    </div>
  );
};

export default BreadTimeModal;
