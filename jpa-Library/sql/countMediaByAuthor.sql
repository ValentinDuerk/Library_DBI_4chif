create or replace function countMediaByAuthorFunc(authorLastName author.lastName%type) return pls_integer
is
    cnt pls_integer :=0;
begin
    select count(*) into cnt
    from author_media ma
    join author a on a.id = ma.author_id
    where a.lastname like authorLastName;
    return cnt;
exception
    when others then return -1;
end;

/

create or replace procedure countMediaByAuthor(authorLastName author.lastName%type, mediaCount out pls_integer)
as
begin
    mediaCount := countMediaByAuthorFunc(authorLastName);
end;