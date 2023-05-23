export default function TitleWithIcon({ iconName, head }) {
    return (
    <div className="d-flex gap-4 px-5">
        <i className={`bi icon-size ${iconName}`}></i>

        <div className="d-flex flex-column align-items-start">
            <h1 className="mb-1"><strong>{head}</strong></h1>
        </div>
    </div>
    );
}