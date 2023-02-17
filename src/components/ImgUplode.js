import { useEffect, useState } from "react";

const ImgUplode = () => {
  const [fileImage, setFileImage] = useState();

  // 파일 저장
  const saveFileImage = (e) => {
    //    setFileImage(URL.createObjectURL(e.target.files[e]));
    const temp = [];
    const fileImage = e.target.fileImage;

    for (let i = 0; i < fileImage.length; i++) {
      temp.push({
        id: fileImage[i].name,
        file: fileImage[i],
        url: URL.createObjectURL(fileImage[i]),
      });
    }
    saveFileImage(temp.concat(fileImage));
  };

  // 파일 삭제
  const deleteFileImage = () => {
    URL.revokeObjectURL(fileImage);
    setFileImage("");
  };

  return (
    <>
      <h1>이미지 미리보기</h1>
      <table>
        <tbody>
          <tr>
            <th>이미지</th>
            <td>
              <div>
                {fileImage && (
                  <img
                    alt="sample"
                    src={fileImage}
                    style={{ margin: "auto" }}
                  />
                )}
                <div
                  style={{
                    alignItems: "center",
                    justifyContent: "center",
                  }}
                >
                  <input
                    name="imgUpload"
                    type="file"
                    enctype="multipary/form-data"
                    accept="image/*"
                    onChange={saveFileImage}
                  />

                  <button
                    style={{
                      backgroundColor: "gray",
                      color: "white",
                      width: "55px",
                      height: "40px",
                      cursor: "pointer",
                    }}
                    onClick={() => deleteFileImage()}
                  >
                    삭제
                  </button>
                </div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </>
  );
};

export default ImgUplode;
