import { useEffect } from "react";
import "../css/BreadTimeModal.css";

const AddressModal = (props) => {
  const { open, close, header } = props;

  useEffect(() => {
    if (open) {
      window.scrollTo(0, 0);
      document.body.style.overflow = "hidden";
    } else document.body.style.overflow = "unset";
  }, [open]);

  return (
    //modal이 열릴 때 openModal 클래스가 생성된다.
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section>
          <header>
            {header}
            <button className="close" onClick={close}>
              &times;
            </button>
          </header>
          <main>{props.children}</main>
          <footer>
            <button className="close" onClick={close}>
              닫기
            </button>
          </footer>
        </section>
      ) : null}
    </div>
  );
};

export default AddressModal;
